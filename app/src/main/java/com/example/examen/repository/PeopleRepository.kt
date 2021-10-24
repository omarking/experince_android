/*
 * The MIT License (MIT)
 *
 * Designed and developed by 2018 skydoves (Jaewoong Eum)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.examen.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.examen.api.ApiResponse
import com.example.examen.api.PeopleService
import com.example.examen.mappers.PeopleResponseMapper
import com.example.examen.models.Resource
import com.example.examen.models.entity.Person
import com.example.examen.models.network.PeopleResponse
import com.example.examen.models.network.PersonDetail
import com.example.examen.room.PeopleDao
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class PeopleRepository @Inject constructor(
  val peopleService: PeopleService,
  val peopleDao: PeopleDao
) : Repository {

  init {
    Timber.d("Injection PeopleRepository")
  }

  fun loadPeople(page: Int): LiveData<Resource<List<Person>>> {
    return object : NetworkBoundRepository<List<Person>, PeopleResponse, PeopleResponseMapper>() {
      override fun saveFetchData(items: PeopleResponse) {
        for (item in items.results) {
          item.page = page
        }
        peopleDao.insertPeople(items.results)
      }

      override fun shouldFetch(data: List<Person>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Person>> {
        return peopleDao.getPeople(page_ = page)
      }

      override fun fetchService(): LiveData<ApiResponse<PeopleResponse>> {
        return peopleService.fetchPopularPeople(page = page)
      }

      override fun mapper(): PeopleResponseMapper {
        return PeopleResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }
}
package com.example.examen.view.ui.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.examen.models.Keyword
import com.example.examen.models.Resource
import com.example.examen.models.Video
import com.example.examen.repository.MovieRepository
import com.example.examen.utils.AbsentLiveData
import javax.inject.Inject
import timber.log.Timber

class MovieDetailViewModel @Inject constructor(
  private val repository: MovieRepository
) : ViewModel() {

  private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val keywordListLiveData: LiveData<Resource<List<Keyword>>>
  val videoListLiveData: LiveData<Resource<List<Video>>>

  init {
    Timber.d("Injection MovieDetailViewModel")

    this.keywordListLiveData = movieIdLiveData.switchMap {
      movieIdLiveData.value?.let {
        repository.loadKeywordList(it)
      } ?: AbsentLiveData.create()
    }

    this.videoListLiveData = movieIdLiveData.switchMap {
      movieIdLiveData.value?.let {
        repository.loadVideoList(it)
      } ?: AbsentLiveData.create()
    }
  }

  fun postMovieId(id: Int) = movieIdLiveData.postValue(id)
}

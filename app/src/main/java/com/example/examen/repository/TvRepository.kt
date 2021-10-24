package com.example.examen.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.examen.api.ApiResponse
import com.example.examen.api.TvService
import com.example.examen.mappers.KeywordResponseMapper
import com.example.examen.mappers.ReviewResponseMapper
import com.example.examen.mappers.VideoResponseMapper
import com.example.examen.models.Keyword
import com.example.examen.models.Resource
import com.example.examen.models.Review
import com.example.examen.models.Video
import com.example.examen.models.network.KeywordListResponse
import com.example.examen.models.network.ReviewListResponse
import com.example.examen.models.network.VideoListResponse
import com.example.examen.room.TvDao
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
class TvRepository @Inject constructor(
  val service: TvService,
  val tvDao: TvDao
) : Repository {

  init {
    Timber.d("Injection TvRepository")
  }

  fun loadKeywordList(id: Int): LiveData<Resource<List<Keyword>>> {
    return object : NetworkBoundRepository<List<Keyword>, KeywordListResponse, KeywordResponseMapper>() {
      override fun saveFetchData(items: KeywordListResponse) {
        val tv = tvDao.getTv(id_ = id)
        tv.keywords = items.keywords
        tvDao.updateTv(tv = tv)
      }

      override fun shouldFetch(data: List<Keyword>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Keyword>> {
        val movie = tvDao.getTv(id_ = id)
        val data: MutableLiveData<List<Keyword>> = MutableLiveData()
        data.postValue(movie.keywords)
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<KeywordListResponse>> {
        return service.fetchKeywords(id = id)
      }

      override fun mapper(): KeywordResponseMapper {
        return KeywordResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }

  fun loadVideoList(id: Int): LiveData<Resource<List<Video>>> {
    return object : NetworkBoundRepository<List<Video>, VideoListResponse, VideoResponseMapper>() {
      override fun saveFetchData(items: VideoListResponse) {
        val tv = tvDao.getTv(id_ = id)
        tv.videos = items.results
        tvDao.updateTv(tv = tv)
      }

      override fun shouldFetch(data: List<Video>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Video>> {
        val movie = tvDao.getTv(id_ = id)
        val data: MutableLiveData<List<Video>> = MutableLiveData()
        data.postValue(movie.videos)
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<VideoListResponse>> {
        return service.fetchVideos(id = id)
      }

      override fun mapper(): VideoResponseMapper {
        return VideoResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }

  fun loadReviewsList(id: Int): LiveData<Resource<List<Review>>> {
    return object : NetworkBoundRepository<List<Review>, ReviewListResponse, ReviewResponseMapper>() {
      override fun saveFetchData(items: ReviewListResponse) {
        val tv = tvDao.getTv(id_ = id)
        tv.reviews = items.results
        tvDao.updateTv(tv = tv)
      }

      override fun shouldFetch(data: List<Review>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Review>> {
        val movie = tvDao.getTv(id_ = id)
        val data: MutableLiveData<List<Review>> = MutableLiveData()
        data.postValue(movie.reviews)
        return data
      }

      override fun fetchService(): LiveData<ApiResponse<ReviewListResponse>> {
        return service.fetchReviews(id = id)
      }

      override fun mapper(): ReviewResponseMapper {
        return ReviewResponseMapper()
      }

      override fun onFetchFailed(message: String?) {
        Timber.d("onFetchFailed : $message")
      }
    }.asLiveData()
  }
}

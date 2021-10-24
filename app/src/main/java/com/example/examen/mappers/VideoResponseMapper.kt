package com.example.examen.mappers

import com.example.examen.models.network.VideoListResponse

class VideoResponseMapper : NetworkResponseMapper<VideoListResponse> {
  override fun onLastPage(response: VideoListResponse): Boolean {
    return true
  }
}

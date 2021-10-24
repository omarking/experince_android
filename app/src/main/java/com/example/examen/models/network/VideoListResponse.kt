package com.example.examen.models.network

import com.example.examen.models.NetworkResponseModel
import com.example.examen.models.Video

data class VideoListResponse(
  val id: Int,
  val results: List<Video>
) : NetworkResponseModel

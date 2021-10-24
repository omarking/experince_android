package com.example.examen.models.network

import com.example.examen.models.NetworkResponseModel
import com.example.examen.models.entity.Movie

data class DiscoverMovieResponse(
  val page: Int,
  val results: List<Movie>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel

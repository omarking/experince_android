package com.example.examen.models.network

import com.example.examen.models.NetworkResponseModel
import com.example.examen.models.entity.Tv

data class DiscoverTvResponse(
  val page: Int,
  val results: List<Tv>,
  val total_results: Int,
  val total_pages: Int
) : NetworkResponseModel

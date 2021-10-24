package com.example.examen.models.network

import com.example.examen.models.NetworkResponseModel
import com.example.examen.models.Review

class ReviewListResponse(
  val id: Int,
  val page: Int,
  val results: List<Review>,
  val total_pages: Int,
  val total_results: Int
) : NetworkResponseModel

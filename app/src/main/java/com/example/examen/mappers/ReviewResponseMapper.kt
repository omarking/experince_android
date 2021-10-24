package com.example.examen.mappers

import com.example.examen.models.network.ReviewListResponse

class ReviewResponseMapper : NetworkResponseMapper<ReviewListResponse> {
  override fun onLastPage(response: ReviewListResponse): Boolean {
    return true
  }
}

package com.example.examen.mappers

import com.example.examen.models.network.KeywordListResponse

class KeywordResponseMapper : NetworkResponseMapper<KeywordListResponse> {
  override fun onLastPage(response: KeywordListResponse): Boolean {
    return true
  }
}

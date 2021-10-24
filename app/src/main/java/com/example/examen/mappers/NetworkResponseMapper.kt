package com.example.examen.mappers

import com.example.examen.models.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
  fun onLastPage(response: FROM): Boolean
}

package com.example.examen.models.network

import com.example.examen.models.Keyword
import com.example.examen.models.NetworkResponseModel

data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel

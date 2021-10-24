package com.example.examen.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.ChipGroup
import com.example.examen.api.Api
import com.example.examen.extension.addPrimaryChip
import com.example.examen.extension.bindResource
import com.example.examen.extension.requestGlideListener
import com.example.examen.extension.visible
import com.example.examen.models.Keyword
import com.example.examen.models.Resource
import com.example.examen.models.entity.Movie
import com.example.examen.models.entity.Tv
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("visibilityByResource")
fun bindVisibilityByResource(view: View, resource: Resource<List<Any>>?) {
  view.bindResource(resource) {
    it.data.whatIfNotNull {
      view.visible()
    }
  }
}

@BindingAdapter("mapKeywordList")
fun bindMapKeywordList(view: ChipGroup, resource: Resource<List<Keyword>>?) {
  view.bindResource(resource) {
    it.data.whatIfNotNullOrEmpty { keywords ->
      view.visible()
      keywords.forEach { keyword -> view.addPrimaryChip(keyword.name) }
    }
  }
}
/*
@BindingAdapter("biography")
fun bindBiography(view: TextView, resource: Resource<PersonDetail>?) {
  view.bindResource(resource) {
    view.text = it.data?.biography
  }
}

@BindingAdapter("nameTags")
fun bindTags(view: ChipGroup, resource: Resource<PersonDetail>?) {
  view.bindResource(resource) {
    it.data?.also_known_as.whatIfNotNullOrEmpty { knows ->
      knows.forEach { know -> view.addPrimaryChip(know) }
      view.visible()
    }
  }
}*/

@BindingAdapter("bindReleaseDate")
fun bindReleaseDate(view: TextView, movie: Movie) {
  view.text = "Release Date : ${movie.release_date}"
}

@BindingAdapter("bindAirDate")
fun bindAirDate(view: TextView, tv: Tv) {
  view.text = "First Air Date : ${tv.first_air_date}"
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, movie: Movie) {
  if (movie.backdrop_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(movie.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else {
    Glide.with(view.context).load(Api.getBackdropPath(movie.poster_path!!))
      .listener(view.requestGlideListener())
      .into(view)
  }
}

@BindingAdapter("bindBackDrop")
fun bindBackDrop(view: ImageView, tv: Tv) {
  if (tv.backdrop_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(tv.backdrop_path))
      .listener(view.requestGlideListener())
      .into(view)
  } else if (tv.poster_path != null) {
    Glide.with(view.context).load(Api.getBackdropPath(tv.poster_path))
      .listener(view.requestGlideListener())
      .into(view)
  }
}
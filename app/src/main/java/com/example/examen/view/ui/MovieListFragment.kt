package com.example.examen.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.examen.R
import com.example.examen.compose.ViewModelFragment
import com.example.examen.databinding.MainFragmentMovieBinding
import com.example.examen.view.adapter.MovieListAdapter

class MovieListFragment : ViewModelFragment() {

  private val viewModel: MainActivityViewModel by injectActivityVIewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding<MainFragmentMovieBinding>(inflater, R.layout.main_fragment_movie, container)
      .apply {
        viewModel = this@MovieListFragment.viewModel.apply { postMoviePage(1) }
        lifecycleOwner = this@MovieListFragment
        adapter = MovieListAdapter()
      }.root
  }
}

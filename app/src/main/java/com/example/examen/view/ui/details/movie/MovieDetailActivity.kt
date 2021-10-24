package com.example.examen.view.ui.details.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.examen.R
import com.example.examen.compose.ViewModelActivity
import com.example.examen.databinding.ActivityMovieDetailBinding
import com.example.examen.extension.applyToolbarMargin
import com.example.examen.extension.simpleToolbarWithHome
import com.example.examen.models.entity.Movie
import com.example.examen.view.adapter.VideoListAdapter
import com.skydoves.whatif.whatIfNotNull
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : ViewModelActivity() {

  private val viewModel: MovieDetailViewModel by injectViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding<ActivityMovieDetailBinding>(R.layout.activity_movie_detail).run {
      lifecycleOwner = this@MovieDetailActivity
      viewModel = this@MovieDetailActivity.viewModel.apply { postMovieId(getMovieFromIntent().id) }
      movie = getMovieFromIntent()
      videoAdapter = VideoListAdapter()
    }
    initializeUI()
  }

  private fun initializeUI() {
    applyToolbarMargin(movie_detail_toolbar)
    simpleToolbarWithHome(movie_detail_toolbar, getMovieFromIntent().title)
  }

  private fun getMovieFromIntent() = intent.getParcelableExtra(movieId) as Movie

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val movieId = "movie"
    fun startActivityModel(context: Context?, movie: Movie) {
      context.whatIfNotNull {
        val intent = Intent(it, MovieDetailActivity::class.java).apply { putExtra(movieId, movie) }
        it.startActivity(intent)
      }
    }
  }
}

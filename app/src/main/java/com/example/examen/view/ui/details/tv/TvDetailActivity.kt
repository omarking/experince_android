package com.example.examen.view.ui.details.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.examen.R
import com.example.examen.compose.ViewModelActivity
import com.example.examen.databinding.ActivityTvDetailBinding
import com.example.examen.extension.applyToolbarMargin
import com.example.examen.extension.simpleToolbarWithHome
import com.example.examen.models.entity.Tv
import com.example.examen.view.adapter.VideoListAdapter
import com.skydoves.whatif.whatIfNotNull
import kotlinx.android.synthetic.main.activity_tv_detail.*

class TvDetailActivity : ViewModelActivity() {

  private val viewModel: TvDetailViewModel by injectViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding<ActivityTvDetailBinding>(R.layout.activity_tv_detail).run {
      lifecycleOwner = this@TvDetailActivity
      viewModel = this@TvDetailActivity.viewModel.apply { postTvId(getTvFromIntent().id) }
      tv = getTvFromIntent()
      videoAdapter = VideoListAdapter()
    }
    initializeUI()
  }

  private fun initializeUI() {
    applyToolbarMargin(tv_detail_toolbar)
    simpleToolbarWithHome(tv_detail_toolbar, getTvFromIntent().name)
  }

  private fun getTvFromIntent() = intent.getParcelableExtra(tvId) as Tv

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) onBackPressed()
    return false
  }

  companion object {
    private const val tvId = "tv"
    fun startActivityModel(context: Context?, tv: Tv) {
      context.whatIfNotNull {
        val intent = Intent(it, TvDetailActivity::class.java).apply { putExtra(tvId, tv) }
        it.startActivity(intent)
      }
    }
  }
}

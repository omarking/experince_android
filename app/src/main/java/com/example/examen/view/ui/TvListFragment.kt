package com.example.examen.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.examen.R
import com.example.examen.compose.ViewModelFragment
import com.example.examen.databinding.MainFragmentTvBinding
import com.example.examen.view.adapter.TvListAdapter

class TvListFragment : ViewModelFragment() {

  private val viewModel: MainActivityViewModel by injectActivityVIewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding<MainFragmentTvBinding>(inflater, R.layout.main_fragment_tv, container)
      .apply {
        viewModel = this@TvListFragment.viewModel.apply { postTvPage(1) }
        lifecycleOwner = this@TvListFragment
        adapter = TvListAdapter()
      }.root
  }
}

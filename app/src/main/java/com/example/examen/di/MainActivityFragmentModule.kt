package com.example.examen.di

import com.example.examen.di.annotations.FragmentScope
import com.example.examen.view.ui.MovieListFragment
import com.example.examen.view.ui.TvListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun contributeMovieListFragment(): MovieListFragment

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun contributeTvListFragment(): TvListFragment
  }

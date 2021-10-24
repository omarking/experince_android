package com.example.examen.di

import com.example.examen.compose.ViewModelActivity
import com.example.examen.compose.ViewModelFragment
import com.example.examen.di.annotations.ActivityScope
import com.example.examen.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ComposeModule {

  @ActivityScope
  @ContributesAndroidInjector
  internal abstract fun contributeViewModelActivity(): ViewModelActivity

  @FragmentScope
  @ContributesAndroidInjector
  internal abstract fun contributeViewModelFragment(): ViewModelFragment
}

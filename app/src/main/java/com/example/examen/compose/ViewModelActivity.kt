package com.example.examen.compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * ViewModelActivity is an abstract class for request dependency injection and
 * provides implementations of [ViewModel] and [ViewDataBinding] from an abstract information.
 * Do not modify this class. This is a first-level abstraction class.
 * If you want to add more specifications, make another class which extends [ViewModelActivity].
 */
abstract class ViewModelActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
  }

  protected inline fun <reified VM : ViewModel>
    injectViewModels(): Lazy<VM> = viewModels { viewModelFactory }

  protected inline fun <reified T : ViewDataBinding> binding(
    @LayoutRes resId: Int
  ): T = DataBindingUtil.setContentView(this, resId)
}

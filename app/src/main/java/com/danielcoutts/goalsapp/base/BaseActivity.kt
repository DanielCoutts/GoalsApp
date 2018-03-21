package com.danielcoutts.goalsapp.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlin.reflect.KClass

abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {

    protected val compositeDisposable = CompositeDisposable()

    protected lateinit var viewModel: T

    abstract val viewModelClass: KClass<T>

    abstract fun subscribeToStreams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass.java)
    }

    override fun onResume() {
        super.onResume()
        subscribeToStreams()
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }
}
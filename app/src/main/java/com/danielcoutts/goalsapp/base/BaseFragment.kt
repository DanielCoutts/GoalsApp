package com.danielcoutts.goalsapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kotlin.reflect.KClass

abstract class BaseFragment<T: ViewModel> : Fragment() {

    protected val compositeDisposable = CompositeDisposable()

    protected lateinit var viewModel: T

    abstract val viewModelClass: KClass<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(viewModelClass.java)
    }

    override fun onResume() {
        super.onResume()
        if (view != null) {
            subscribeToStreams(view!!)
        }
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }

    abstract fun subscribeToStreams(view: View)
}
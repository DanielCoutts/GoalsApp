package com.danielcoutts.goalsapp.sections.main.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.danielcoutts.goalsapp.R
import com.danielcoutts.goalsapp.base.BaseFragment
import com.danielcoutts.goalsapp.sections.main.MainViewModel
import com.pawegio.kandroid.textWatcher
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_goal_list.view.*
import kotlin.reflect.KClass

class GoalListFragment : BaseFragment<MainViewModel>() {

    override val viewModelClass: KClass<MainViewModel> = MainViewModel::class

    override fun subscribeToStreams(view: View) {
        viewModel.viewState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { view.textView.text = it.testString1 }
                )
                .addTo(compositeDisposable)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_goal_list, container, false)

        view.editText.textWatcher {
            afterTextChanged {
                viewModel.updateString(it.toString())
            }
        }

        return view
    }

}

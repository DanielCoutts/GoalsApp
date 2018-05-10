package com.danielcoutts.goalsapp.sections.main.fragments

import android.view.View
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class WeeklyGoalListFragment : BaseGoalListFragment(Recurrence.WEEKLY) {

    override fun subscribeToStreams(view: View) {
        viewModel.viewState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {

                }
                .addTo(compositeDisposable)
    }
}
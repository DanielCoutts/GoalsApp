package com.danielcoutts.goalsapp.sections.main.fragments

import android.view.View
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.sections.main.data.GoalListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DailyGoalListFragment : BaseGoalListFragment() {

    override fun subscribeToStreams(view: View) {
        super.subscribeToStreams(view)

        viewModel.dayViewState()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    adapter.setGoalListData(GoalListData(it.goals, it.logs))

                }
                .addTo(compositeDisposable)
    }
}
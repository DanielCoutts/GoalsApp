package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.sections.main.data.ViewState
import io.reactivex.Flowable
import io.reactivex.rxkotlin.Flowables

class MainViewModel : BaseViewModel() {

    private val model = MainModel()

    override fun onCleared() {
        super.onCleared()
    }

    fun dayViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.dailyGoals, model.dailyGoalLogs) {
            goals, logs -> ViewState(goals, logs)
        }
    }

    fun weekViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.weeklyGoals, model.weeklyGoalLogs) {
            goals, logs -> ViewState(goals, logs)
        }
    }

    fun monthViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.monthlyGoals, model.monthlyGoalLogs) {
            goals, logs -> ViewState(goals, logs)
        }
    }

}
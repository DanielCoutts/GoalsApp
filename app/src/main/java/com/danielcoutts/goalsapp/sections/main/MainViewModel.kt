package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.sections.main.data.ViewState
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {

    private val model = MainModel()

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun dayViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.dailyGoals, model.dailyGoalLogs) { goals, logs ->
            ViewState(goals, logs)
        }
    }

    fun weekViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.weeklyGoals, model.weeklyGoalLogs) { goals, logs ->
            ViewState(goals, logs)
        }
    }

    fun monthViewState(): Flowable<ViewState> {
        return Flowables.combineLatest(model.monthlyGoals, model.monthlyGoalLogs) { goals, logs ->
            ViewState(goals, logs)
        }
    }

    fun deleteGoal(goal: Goal) {
        model.deleteGoal(goal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe().addTo(compositeDisposable)
    }

    fun logForGoal(goal: Goal) {
        model.logForGoal(goal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe().addTo(compositeDisposable)
    }

}
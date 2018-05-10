package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.sections.main.data.ViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject

class MainViewModel : BaseViewModel() {

    private val dailyGoals = BehaviorSubject.create<List<Goal>>()
    private val weeklyGoals = BehaviorSubject.create<List<Goal>>()
    private val monthlyGoals = BehaviorSubject.create<List<Goal>>()

    val model = MainModel()

    override fun onCleared() {
        super.onCleared()
    }

    fun viewState(): Observable<ViewState> {
        return Observables.combineLatest(dailyGoals, weeklyGoals, monthlyGoals) {
            daily, weekly, monthly -> ViewState(daily, weekly, monthly)
        }
    }

}
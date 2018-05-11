package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.GoalType
import com.danielcoutts.goalsapp.etc.Recurrence
import com.danielcoutts.goalsapp.sections.create.data.ViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables

class CreateGoalViewModel : BaseViewModel() {

    val model = CreateGoalModel()

    override fun onCleared() {
        super.onCleared()
    }

    fun viewState(): Observable<ViewState> {
        return Observable.just(ViewState(Goal("", Recurrence.DAILY, GoalType.TIME)))
    }

}
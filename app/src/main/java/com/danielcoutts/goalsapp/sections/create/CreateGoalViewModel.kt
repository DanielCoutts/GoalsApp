package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

class CreateGoalViewModel : BaseViewModel() {

    val model = CreateGoalModel()

    override fun onCleared() {
        super.onCleared()
    }

//    fun viewState(): Observable<ViewState> {
//        return Observable.just(ViewState(Goal("", Recurrence.DAILY, GoalType.TIME)))
//    }

    fun createGoal(): Completable = model.createGoal(Goal("Do 5 things", Recurrence.DAILY, 5))

}
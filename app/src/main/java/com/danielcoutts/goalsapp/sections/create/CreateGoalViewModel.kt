package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.base.BaseViewModel

class CreateGoalViewModel : BaseViewModel() {

    val model = CreateGoalModel()

    override fun onCleared() {
        super.onCleared()
    }

//    fun viewState(): Observable<ViewState> {
//        return Observable.just(ViewState(Goal("", Recurrence.DAILY, GoalType.TIME)))
//    }

}
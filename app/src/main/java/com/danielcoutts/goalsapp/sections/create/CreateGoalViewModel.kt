package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class CreateGoalViewModel : BaseViewModel() {

    val model = CreateGoalModel()

    var verb = ""
    var number = 0
    var noun = ""
    var recurrence: Recurrence? = null

    override fun onCleared() {
        super.onCleared()
    }

    fun createGoal(): Single<Boolean> {
        return if (isValid()) {
            model.createGoal(Goal("$verb $number $noun", recurrence!!, number)).toSingleDefault(true)
        } else {
            Single.just(false)
        }
    }

    private fun isValid(): Boolean = !verb.isEmpty() && number > 0 && !noun.isEmpty() && recurrence != null

}
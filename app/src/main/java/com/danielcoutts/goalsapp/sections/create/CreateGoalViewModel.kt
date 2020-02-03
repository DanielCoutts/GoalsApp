package com.danielcoutts.goalsapp.sections.create

import androidx.lifecycle.liveData
import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.Recurrence

class CreateGoalViewModel : BaseViewModel() {

    private val model = CreateGoalModel()

    var verb = ""
    var number = 0
    var noun = ""
    var recurrence: Recurrence? = null

    fun createGoal() = liveData {
        val isValid = isValid()

        if (isValid) {
            model.createGoal(
                    Goal("$verb $number $noun", recurrence!!, number)
            )
        }

        emit(isValid)
    }

    private fun isValid(): Boolean =
            verb.isNotEmpty()
                    && number > 0
                    && noun.isNotEmpty()
                    && recurrence != null

}
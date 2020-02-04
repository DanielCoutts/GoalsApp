package com.danielcoutts.goalsapp.sections.create

import androidx.lifecycle.liveData
import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity
import com.danielcoutts.goalsapp.data.db.etc.GoalType
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.Recurrence

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
                    Goal.Number(
                            title = "$verb $number $noun",
                            recurrence = recurrence!!,
                            target = number
                    )
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
package com.danielcoutts.goalsapp.sections.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.util.Event
import kotlinx.coroutines.launch

class CreateGoalViewModel : BaseViewModel() {

    private val model = CreateGoalModel()

    var verb = ""
    var number = 0
    var noun = ""
    var recurrence: Recurrence? = null

    fun createGoal() = viewModelScope.launch {
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

        _events.postValue(Event(if(isValid) CreateGoalEvent.GOAL_CREATED else CreateGoalEvent.GOAL_CREATION_ERROR))
    }

    private val _events = MutableLiveData<Event<CreateGoalEvent>>()
    val events: LiveData<Event<CreateGoalEvent>> = _events

    private fun isValid(): Boolean =
            verb.isNotEmpty()
                    && number > 0
                    && noun.isNotEmpty()
                    && recurrence != null

    enum class CreateGoalEvent {
        GOAL_CREATED, GOAL_CREATION_ERROR
    }

}
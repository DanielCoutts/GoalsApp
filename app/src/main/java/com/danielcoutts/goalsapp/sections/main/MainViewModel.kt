package com.danielcoutts.goalsapp.sections.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.danielcoutts.goalsapp.base.BaseViewModel
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.sections.main.data.ViewState
import com.danielcoutts.goalsapp.util.combineLatest
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private val model = MainModel()

    fun dayViewState(): LiveData<ViewState> =
            combineLatest(model.dailyGoals(), model.dailyGoalLogs(), ::ViewState)

    fun weekViewState(): LiveData<ViewState> =
            combineLatest(model.weeklyGoals(), model.weeklyGoalLogs(), ::ViewState)

    fun monthViewState(): LiveData<ViewState> =
            combineLatest(model.monthlyGoals(), model.monthlyGoalLogs(), ::ViewState)

    fun deleteGoal(goal: Goal) = viewModelScope.launch {
        model.deleteGoal(goal)
    }

    fun logForGoal(goal: Goal.Number) = viewModelScope.launch {
        model.logForGoal(goal)
    }
}
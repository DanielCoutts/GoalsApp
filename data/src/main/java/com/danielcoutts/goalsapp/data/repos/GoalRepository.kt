package com.danielcoutts.goalsapp.data.repos

import androidx.lifecycle.LiveData
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.models.Goal

interface GoalRepository {
    fun goals(recurrence: Recurrence): LiveData<List<Goal>>
    suspend fun createGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
}
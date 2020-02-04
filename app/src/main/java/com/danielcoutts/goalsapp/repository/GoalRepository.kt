package com.danielcoutts.goalsapp.repository

import androidx.lifecycle.LiveData
import com.danielcoutts.goalsapp.repository.etc.Recurrence

interface GoalRepository {
    fun goals(recurrence: Recurrence): LiveData<List<Goal>>
    suspend fun createGoal(goal: Goal)
    suspend fun deleteGoal(goal: Goal)
}
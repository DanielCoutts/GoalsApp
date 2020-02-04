package com.danielcoutts.goalsapp.repository

import androidx.lifecycle.LiveData
import com.danielcoutts.goalsapp.repository.etc.Recurrence

interface GoalLogRepository {
    fun goalLogs(recurrence: Recurrence): LiveData<List<GoalLog>>
    suspend fun log(goal: Goal.Time, mins: Int)
    suspend fun log(goal: Goal.Number, number: Int)
    suspend fun log(goal: Goal.Checkbox)
}
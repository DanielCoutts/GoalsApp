package com.danielcoutts.goalsapp.data.repos

import androidx.lifecycle.LiveData
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog

interface GoalLogRepository {
    fun goalLogs(recurrence: Recurrence): LiveData<List<GoalLog>>
    suspend fun log(goal: Goal.Time, mins: Int)
    suspend fun log(goal: Goal.Number, number: Int)
    suspend fun log(goal: Goal.Checkbox)
}
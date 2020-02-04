package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.data.db.entities.GoalEntity
import com.danielcoutts.goalsapp.data.db.etc.LocalDateValues
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.repos.GoalLogRepository
import com.danielcoutts.goalsapp.data.repos.GoalLogRepositoryImpl
import com.danielcoutts.goalsapp.data.repos.GoalRepository
import com.danielcoutts.goalsapp.data.repos.GoalRepositoryImpl

class MainModel {
    private val goalRepo: GoalRepository = GoalRepositoryImpl
    private val goalLogRepo: GoalLogRepository = GoalLogRepositoryImpl

    fun dailyGoals() =
            goalRepo.goals(Recurrence.DAILY) // TODO Make distinct

    fun weeklyGoals() =
            goalRepo.goals(Recurrence.WEEKLY) // TODO Make distinct

    fun monthlyGoals() =
            goalRepo.goals(Recurrence.MONTHLY) // TODO Make distinct

    fun dailyGoalLogs() =
            goalLogRepo.goalLogs(Recurrence.DAILY) // TODO Make distinct

    fun weeklyGoalLogs() =
            goalLogRepo.goalLogs(Recurrence.WEEKLY) // TODO Make distinct

    fun monthlyGoalLogs() =
            goalLogRepo.goalLogs(Recurrence.MONTHLY) // TODO Make distinct

    suspend fun deleteGoal(goal: Goal) =
            goalRepo.deleteGoal(goal)

    suspend fun logForGoal(goal: Goal.Number) =
            goalLogRepo.log(goal, 1)
}
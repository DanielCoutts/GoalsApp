package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.application.MyApplication
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.repos.GoalLogRepository
import com.danielcoutts.goalsapp.data.repos.GoalRepository

class MainModel {
    private val goalRepo: GoalRepository = // TODO Update
            MyApplication.instance.appComponent.getGoalRepository()
    private val goalLogRepo: GoalLogRepository = // TODO Update
            MyApplication.instance.appComponent.getGoalLogRepository()

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
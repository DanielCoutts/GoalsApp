package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.data.db.AppDatabase
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity
import com.danielcoutts.goalsapp.data.db.etc.LocalDateValues
import com.danielcoutts.goalsapp.data.models.Recurrence

class MainModel {
    private val goalDao = AppDatabase.instance.goalDao()
    private val goalLogDao = AppDatabase.instance.goalLogDao()

    fun dailyGoals() =
            goalDao.goals(Recurrence.DAILY) // TODO Make distinct

    fun weeklyGoals() =
            goalDao.goals(Recurrence.WEEKLY) // TODO Make distinct

    fun monthlyGoals() =
            goalDao.goals(Recurrence.MONTHLY) // TODO Make distinct

    fun dailyGoalLogs() =
            goalLogDao.logs(LocalDateValues.today, Recurrence.DAILY) // TODO Make distinct

    fun weeklyGoalLogs() =
            goalLogDao.logs(LocalDateValues.week, Recurrence.WEEKLY) // TODO Make distinct

    fun monthlyGoalLogs() =
            goalLogDao.logs(LocalDateValues.month, Recurrence.MONTHLY) // TODO Make distinct

    suspend fun deleteGoal(goal: GoalEntity) =
            goalDao.deleteGoal(goal)

    suspend fun logForGoal(goal: GoalEntity) =
            goalLogDao.logForGoal(goal)
}
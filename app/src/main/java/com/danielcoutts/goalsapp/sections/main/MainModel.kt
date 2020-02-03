package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.db.AppDatabase
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.LocalDateConstants
import com.danielcoutts.goalsapp.etc.Recurrence

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
            goalLogDao.logs(LocalDateConstants.today, Recurrence.DAILY) // TODO Make distinct

    fun weeklyGoalLogs() =
            goalLogDao.logs(LocalDateConstants.week, Recurrence.WEEKLY) // TODO Make distinct

    fun monthlyGoalLogs() =
            goalLogDao.logs(LocalDateConstants.month, Recurrence.MONTHLY) // TODO Make distinct

    suspend fun deleteGoal(goal: Goal) =
            goalDao.deleteGoal(goal)

    suspend fun logForGoal(goal: Goal) =
            goalLogDao.logForGoal(goal)
}
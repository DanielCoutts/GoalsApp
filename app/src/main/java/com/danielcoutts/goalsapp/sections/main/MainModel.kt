package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.db.AppDatabase
import com.danielcoutts.goalsapp.etc.LocalDateConstants
import com.danielcoutts.goalsapp.etc.Recurrence

class MainModel {
    private val goalDao = AppDatabase.instance.goalDao()
    private val goalLogDao = AppDatabase.instance.goalLogDao()

    val dailyGoals = goalDao.goals(Recurrence.DAILY).distinctUntilChanged()

    val weeklyGoals = goalDao.goals(Recurrence.WEEKLY).distinctUntilChanged()

    val monthlyGoals = goalDao.goals(Recurrence.MONTHLY).distinctUntilChanged()

    val dailyGoalLogs = goalLogDao.logs(LocalDateConstants.today, Recurrence.DAILY).distinctUntilChanged()

    val weeklyGoalLogs = goalLogDao.logs(LocalDateConstants.week, Recurrence.WEEKLY).distinctUntilChanged()

    val monthlyGoalLogs = goalLogDao.logs(LocalDateConstants.month, Recurrence.MONTHLY).distinctUntilChanged()
}
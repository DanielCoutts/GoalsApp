package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.db.AppDatabase
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.LocalDateConstants
import com.danielcoutts.goalsapp.etc.Recurrence
import io.reactivex.Completable

class MainModel {
    private val goalDao = AppDatabase.instance.goalDao()
    private val goalLogDao = AppDatabase.instance.goalLogDao()

    val dailyGoals = goalDao.goals(Recurrence.DAILY).distinctUntilChanged()

    val weeklyGoals = goalDao.goals(Recurrence.WEEKLY).distinctUntilChanged()

    val monthlyGoals = goalDao.goals(Recurrence.MONTHLY).distinctUntilChanged()

    val dailyGoalLogs = goalLogDao.logs(LocalDateConstants.today, Recurrence.DAILY).distinctUntilChanged()

    val weeklyGoalLogs = goalLogDao.logs(LocalDateConstants.week, Recurrence.WEEKLY).distinctUntilChanged()

    val monthlyGoalLogs = goalLogDao.logs(LocalDateConstants.month, Recurrence.MONTHLY).distinctUntilChanged()

    fun deleteGoal(goal: Goal) = Completable.fromAction {
        goalDao.deleteGoal(goal)
    }

    fun logForGoal(goal: Goal) = Completable.fromAction {
        goalLogDao.logForGoal(goal)
    }
}
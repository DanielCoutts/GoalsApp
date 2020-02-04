package com.danielcoutts.goalsapp.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.danielcoutts.goalsapp.data.db.AppDatabase
import com.danielcoutts.goalsapp.data.db.entities.GoalLogEntity
import com.danielcoutts.goalsapp.data.db.etc.LocalDateValues
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog
import com.danielcoutts.goalsapp.data.utils.toEntity
import com.danielcoutts.goalsapp.data.utils.toGoalLog

object GoalLogRepositoryImpl : GoalLogRepository {

    private val goalLogDao by lazy {
        AppDatabase.instance.goalLogDao()
    }

    override fun goalLogs(recurrence: Recurrence): LiveData<List<GoalLog>> =
            logs(recurrence)
                    .map { goals ->
                        goals.map { it.toGoalLog() }
                    }

    override suspend fun log(goal: Goal.Time, mins: Int) {
        goalLogDao.logForGoal(goal.toEntity(), mins)
    }

    override suspend fun log(goal: Goal.Number, number: Int) {
        goalLogDao.logForGoal(goal.toEntity(), number)
    }

    override suspend fun log(goal: Goal.Checkbox) {
        goalLogDao.logForGoal(goal.toEntity(), 1)
    }

    private fun logs(recurrence: Recurrence): LiveData<List<GoalLogEntity>> =
            goalLogDao.logs(
                    date = when (recurrence) {
                        Recurrence.DAILY -> LocalDateValues.today
                        Recurrence.WEEKLY -> LocalDateValues.week
                        Recurrence.MONTHLY -> LocalDateValues.month
                    },
                    recurrence = recurrence
            )
}


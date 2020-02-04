package com.danielcoutts.goalsapp.data.repos

import androidx.lifecycle.map
import com.danielcoutts.goalsapp.data.db.AppDatabase
import com.danielcoutts.goalsapp.data.models.Recurrence
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.utils.toEntity
import com.danielcoutts.goalsapp.data.utils.toGoal

object GoalRepositoryImpl : GoalRepository {

    private val goalDao by lazy {
        AppDatabase.instance.goalDao()
    }

    override fun goals(recurrence: Recurrence) = goalDao.goals(recurrence)
            .map { goals ->
                goals.map { it.toGoal() }
            }

    override suspend fun createGoal(goal: Goal) {
        goalDao.insertGoal(goal.toEntity())
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal.toEntity())
    }
}
package com.danielcoutts.goalsapp.repository

import androidx.lifecycle.map
import com.danielcoutts.goalsapp.repository.db.AppDatabase
import com.danielcoutts.goalsapp.repository.etc.Recurrence
import com.danielcoutts.goalsapp.repository.utils.toGoal

object GoalRepositoryImpl : GoalRepository {

    private val goalDao by lazy {
        AppDatabase.instance.goalDao()
    }

    override fun goals(recurrence: Recurrence) = goalDao.goals(recurrence)
            .map { goals ->
                goals.map { it.toGoal() }
            }

    override suspend fun createGoal(goal: Goal) {
        // TODO
    }

    override suspend fun deleteGoal(goal: Goal) {
        // TODO
    }
}
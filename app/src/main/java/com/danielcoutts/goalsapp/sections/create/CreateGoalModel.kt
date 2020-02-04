package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.repository.db.AppDatabase
import com.danielcoutts.goalsapp.repository.db.entities.GoalEntity

class CreateGoalModel {
    private val goalDao = AppDatabase.instance.goalDao()

    suspend fun createGoal(goal: GoalEntity) =
            goalDao.insertGoal(goal)
}
package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.data.db.AppDatabase
import com.danielcoutts.goalsapp.data.db.entities.GoalEntity

class CreateGoalModel {
    private val goalDao = AppDatabase.instance.goalDao()

    suspend fun createGoal(goal: GoalEntity) =
            goalDao.insertGoal(goal)
}
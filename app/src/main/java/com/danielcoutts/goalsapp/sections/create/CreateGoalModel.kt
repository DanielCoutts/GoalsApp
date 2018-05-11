package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.db.AppDatabase
import com.danielcoutts.goalsapp.db.entities.Goal

class CreateGoalModel {
    private val goalDao = AppDatabase.instance.goalDao()

    fun createGoal(goal: Goal) = goalDao.insertGoal(goal)
}
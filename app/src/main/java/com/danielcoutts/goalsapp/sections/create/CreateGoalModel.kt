package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.application.MyApplication
import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.repos.GoalRepository

class CreateGoalModel {
    private val goalRepo: GoalRepository = // TODO Update
            MyApplication.instance.appComponent.getGoalRepository()

    suspend fun createGoal(goal: Goal) =
            goalRepo.createGoal(goal)
}
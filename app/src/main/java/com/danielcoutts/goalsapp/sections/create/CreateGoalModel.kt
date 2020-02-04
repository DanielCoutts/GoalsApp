package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.repos.GoalRepository
import com.danielcoutts.goalsapp.data.repos.GoalRepositoryImpl

class CreateGoalModel {
    private val goalRepo: GoalRepository = GoalRepositoryImpl

    suspend fun createGoal(goal: Goal) =
            goalRepo.createGoal(goal)
}
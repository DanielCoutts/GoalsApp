package com.danielcoutts.goalsapp.sections.create

import com.danielcoutts.goalsapp.db.AppDatabase
import com.danielcoutts.goalsapp.db.entities.Goal
import io.reactivex.Completable
import io.reactivex.functions.Action

class CreateGoalModel {
    private val goalDao = AppDatabase.instance.goalDao()

    fun createGoal(goal: Goal): Completable {
        return Completable.fromAction {
            goalDao.insertGoal(goal)
        }
    }
}
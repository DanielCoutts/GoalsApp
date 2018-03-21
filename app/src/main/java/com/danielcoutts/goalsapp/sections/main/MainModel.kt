package com.danielcoutts.goalsapp.sections.main

import com.danielcoutts.goalsapp.db.AppDatabase

class MainModel {
    private val goalDao = AppDatabase.instance.goalDao()

    val goals = goalDao.goals()
}
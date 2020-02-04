package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.repository.db.entities.*

data class ViewState(
        val goals: List<GoalEntity>,
        val logs: List<GoalLogEntity>
)

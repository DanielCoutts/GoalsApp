package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.db.entities.*

data class ViewState(
        val goals: List<Goal>,
        val logs: List<GoalLog>
)

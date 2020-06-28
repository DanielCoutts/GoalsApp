package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.data.models.Goal
import com.danielcoutts.goalsapp.data.models.GoalLog

data class GoalListData(
        val goals: List<Goal>,
        val logs: List<GoalLog>
)
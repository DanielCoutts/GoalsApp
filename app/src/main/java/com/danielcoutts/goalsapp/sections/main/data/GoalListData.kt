package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.db.entities.GoalLog

class GoalListData(
        val goals: List<Goal>,
        val logs: List<GoalLog>
)
package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.repository.db.entities.GoalEntity
import com.danielcoutts.goalsapp.repository.db.entities.GoalLogEntity

class GoalListData(
        val goals: List<GoalEntity>,
        val logs: List<GoalLogEntity>
)
package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.db.entities.CheckboxGoalLog
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.db.entities.NumberGoalLog
import com.danielcoutts.goalsapp.db.entities.TimeGoalLog

data class ViewState(
        val dailyGoals: List<Goal>,
        val weeklyGoals: List<Goal>,
        val monthlyGoals: List<Goal>,

        val timeGoalLogs: List<TimeGoalLog>,
        val numberGoalLogs: List<NumberGoalLog>,
        val checkboxGoalLogs: List<CheckboxGoalLog>
)

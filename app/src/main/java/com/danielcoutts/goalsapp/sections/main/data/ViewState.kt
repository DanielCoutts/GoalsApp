package com.danielcoutts.goalsapp.sections.main.data

import com.danielcoutts.goalsapp.db.entities.Goal

data class ViewState(
        val dailyGoals: List<Goal>,
        val weeklyGoals: List<Goal>,
        val monthlyGoals: List<Goal>
)

package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Entity
import org.joda.time.LocalDate

@Entity(tableName = "checkbox_goal_logs", inheritSuperIndices = true)
class CheckboxGoalLog(goalId: Long, date: LocalDate) : GoalLog(goalId, date)
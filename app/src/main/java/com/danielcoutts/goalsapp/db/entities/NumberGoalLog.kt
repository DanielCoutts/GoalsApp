package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Entity
import org.joda.time.LocalDate

@Entity(tableName = "number_goal_logs", inheritSuperIndices = true)
class NumberGoalLog(goalId: Long, date: LocalDate, var numberLogged: Int) : GoalLog(goalId, date)
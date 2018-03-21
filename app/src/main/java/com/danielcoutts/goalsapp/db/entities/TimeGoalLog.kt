package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Entity
import com.danielcoutts.goalsapp.etc.Duration
import org.joda.time.LocalDate

@Entity(tableName = "time_goal_logs", inheritSuperIndices = true)
class TimeGoalLog(goalId: Long, date: LocalDate, var timeLogged: Duration) : GoalLog(goalId, date)
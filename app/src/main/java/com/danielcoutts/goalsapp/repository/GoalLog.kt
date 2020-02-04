package com.danielcoutts.goalsapp.repository

import com.danielcoutts.goalsapp.repository.etc.Recurrence
import java.time.LocalDate

sealed class GoalLog {
    data class Time(
            override val id: Long,
            override val goalId: Long,
            override val recurrence: Recurrence,
            override val date: LocalDate,
            val minsLogged: Int
    ) : GoalLog()

    data class Number(
            override val id: Long,
            override val goalId: Long,
            override val recurrence: Recurrence,
            override val date: LocalDate,
            val numberLogged: Int
    ) : GoalLog()

    data class Checkbox(
            override val id: Long,
            override val goalId: Long,
            override val recurrence: Recurrence,
            override val date: LocalDate
    ) : GoalLog()

    abstract val id: Long
    abstract val goalId: Long
    abstract val recurrence: Recurrence
    abstract val date: LocalDate
}
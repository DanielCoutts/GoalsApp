package com.danielcoutts.goalsapp.repository

import com.danielcoutts.goalsapp.repository.db.entities.GoalEntity
import com.danielcoutts.goalsapp.repository.etc.GoalType
import com.danielcoutts.goalsapp.repository.etc.Recurrence
import java.time.LocalDate

sealed class Goal {
    data class Time(
            override val id: Long,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate,
            val targetMins: Int
    ) : Goal()

    data class Number(
            override val id: Long,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate,
            val target: Int
    ) : Goal()

    data class Checkbox(
            override val id: Long,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate
    ) : Goal()

    abstract val id: Long
    abstract val title: String
    abstract val recurrence: Recurrence
    abstract val dateCreated: LocalDate
}
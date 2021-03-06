package com.danielcoutts.goalsapp.data.models

import java.time.LocalDate

sealed class Goal {
    data class Time(
            override val id: Long = 0,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate = LocalDate.now(),
            val targetMins: Int
    ) : Goal()

    data class Number(
            override val id: Long = 0,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate = LocalDate.now(),
            val target: Int
    ) : Goal()

    data class Checkbox(
            override val id: Long = 0,
            override val title: String,
            override val recurrence: Recurrence,
            override val dateCreated: LocalDate = LocalDate.now()
    ) : Goal()

    abstract val id: Long
    abstract val title: String
    abstract val recurrence: Recurrence
    abstract val dateCreated: LocalDate
}
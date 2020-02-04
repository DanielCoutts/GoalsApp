package com.danielcoutts.goalsapp.util

import java.time.DayOfWeek
import java.time.LocalDate

fun LocalDate.withDayOfWeek(
        dayOfWeek: DayOfWeek
): LocalDate =
        plusDays(
                (dayOfWeek.value - this.dayOfWeek.value)
                        .toLong()
        )
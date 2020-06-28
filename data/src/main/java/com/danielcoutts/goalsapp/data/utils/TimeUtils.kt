package com.danielcoutts.goalsapp.data.utils

import java.time.DayOfWeek
import java.time.LocalDate

internal fun LocalDate.withDayOfWeek(
        dayOfWeek: DayOfWeek
): LocalDate =
        plusDays(
                (dayOfWeek.value - this.dayOfWeek.value)
                        .toLong()
        )
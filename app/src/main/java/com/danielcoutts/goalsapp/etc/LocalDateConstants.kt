package com.danielcoutts.goalsapp.etc

import java.time.DayOfWeek
import java.time.LocalDate

object LocalDateConstants {
    val today get() = LocalDate.now()
    val week get() = today.withDayOfWeek(DayOfWeek.MONDAY)
    val month get() = today.withDayOfMonth(1)

    private fun LocalDate.withDayOfWeek(
            dayOfWeek: DayOfWeek
    ): LocalDate =
            plusDays(
                    (dayOfWeek.value - this.dayOfWeek.value)
                            .toLong()
            )
}
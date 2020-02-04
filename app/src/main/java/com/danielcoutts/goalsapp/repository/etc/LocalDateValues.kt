package com.danielcoutts.goalsapp.repository.etc

import com.danielcoutts.goalsapp.util.withDayOfWeek
import java.time.DayOfWeek
import java.time.LocalDate

object LocalDateValues {
    val today: LocalDate get() = LocalDate.now()
    val week: LocalDate get() = today.withDayOfWeek(DayOfWeek.MONDAY)
    val month: LocalDate get() = today.withDayOfMonth(1)
}
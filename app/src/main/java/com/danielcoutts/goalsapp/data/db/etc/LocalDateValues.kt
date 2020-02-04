package com.danielcoutts.goalsapp.data.db.etc

import com.danielcoutts.goalsapp.util.withDayOfWeek
import java.time.DayOfWeek
import java.time.LocalDate

internal object LocalDateValues {
    val today: LocalDate get() = LocalDate.now()
    val week: LocalDate get() = today.withDayOfWeek(DayOfWeek.MONDAY)
    val month: LocalDate get() = today.withDayOfMonth(1)
}
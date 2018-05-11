package com.danielcoutts.goalsapp.etc

import org.joda.time.LocalDate

object LocalDateConstants {
    val today get() = LocalDate.now()
    val week get() = today.withDayOfWeek(1)
    val month get() = today.withDayOfMonth(1)
}
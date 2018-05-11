package com.danielcoutts.goalsapp.etc

enum class Recurrence {
    DAILY(), WEEKLY(), MONTHLY();

    override fun toString(): String {
        return when(this) {
            DAILY -> "Today"
            WEEKLY -> "This week"
            MONTHLY -> "This month"
        }
    }
}
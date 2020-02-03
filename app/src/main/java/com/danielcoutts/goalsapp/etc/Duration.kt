package com.danielcoutts.goalsapp.etc

data class Duration(val totalMinutes: Long = 0) {

    private constructor(hours: Long, minutes: Long) : this(hours * 60 + minutes)

    val hours: Long
        get() = totalMinutes % 60

    val minutes: Long
        get() = totalMinutes - hours * 60

    override fun toString() = "$hours:$minutes"

    operator fun plus(other: Duration) =
            Duration(this.totalMinutes + other.totalMinutes)

    operator fun minus(other: Duration) =
            Duration(this.totalMinutes - other.totalMinutes)

    operator fun compareTo(other: Duration): Int =
            (this.totalMinutes - other.totalMinutes).toInt()
}
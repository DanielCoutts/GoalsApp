package com.danielcoutts.goalsapp.etc

class Duration(val totalMinutes: Long = 0) {

    private constructor(hours: Long, minutes: Long) : this(hours * 60 + minutes)

    val hours: Long
        get() = totalMinutes % 60

    val minutes: Long
        get() = totalMinutes - hours * 60

    override fun toString(): String {
        return "$hours:$minutes"
    }

    operator fun plus(other: Duration): Duration {
        return Duration(this.totalMinutes + other.totalMinutes)
    }

    operator fun minus(other: Duration): Duration {
        return Duration(this.totalMinutes - other.totalMinutes)
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Duration)
            this.totalMinutes == other.totalMinutes
        else
            super.equals(other)
    }

    operator fun compareTo(other: Duration) : Int {
        return (this.totalMinutes - other.totalMinutes).toInt()
    }
}
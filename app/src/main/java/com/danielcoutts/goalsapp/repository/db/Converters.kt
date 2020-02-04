package com.danielcoutts.goalsapp.repository.db

import androidx.room.TypeConverter
import com.danielcoutts.goalsapp.repository.etc.Duration
import com.danielcoutts.goalsapp.repository.etc.GoalType
import com.danielcoutts.goalsapp.repository.etc.Recurrence
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun intToRecurrence(value: Int?): Recurrence? =
            value?.let { Recurrence.values()[it] }

    @TypeConverter
    fun recurrenceToInt(value: Recurrence?): Int? =
            value?.ordinal

    @TypeConverter
    fun intToGoalType(value: Int?): GoalType? =
            value?.let { GoalType.values()[it] }

    @TypeConverter
    fun goalTypeToInt(value: GoalType?): Int? =
            value?.ordinal

    @TypeConverter
    fun longToDuration(value: Long?): Duration? =
            value?.let { Duration(it) }

    @TypeConverter
    fun durationToLong(value: Duration?): Long? =
            value?.totalMinutes

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? =
            value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun localDateToString(value: LocalDate?): String? =
            value?.toString()
}
package com.danielcoutts.goalsapp.db

import androidx.room.TypeConverter
import com.danielcoutts.goalsapp.etc.Duration
import com.danielcoutts.goalsapp.etc.GoalType
import com.danielcoutts.goalsapp.etc.Recurrence
import org.joda.time.LocalDate

class Converters {

    @TypeConverter
    fun intToRecurrence(value: Int?): Recurrence? =
            if (value == null) null
            else Recurrence.values()[value]

    @TypeConverter
    fun recurrenceToInt(value: Recurrence?): Int? = value?.ordinal

    @TypeConverter
    fun intToGoalType(value: Int?): GoalType? =
            if (value == null) null
            else GoalType.values()[value]

    @TypeConverter
    fun goalTypeToInt(value: GoalType?): Int? = value?.ordinal

    @TypeConverter
    fun longToDuration(value: Long?): Duration? =
            if (value == null) null
            else Duration(value)

    @TypeConverter
    fun durationToLong(value: Duration?): Long? = value?.totalMinutes

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? =
            if (value == null) null
            else LocalDate(value)

    @TypeConverter
    fun localDateToString(value: LocalDate?): String? = value.toString()
}
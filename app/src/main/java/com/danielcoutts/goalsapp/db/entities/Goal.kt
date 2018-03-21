package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.danielcoutts.goalsapp.etc.Duration
import com.danielcoutts.goalsapp.etc.GoalType
import com.danielcoutts.goalsapp.etc.Recurrence
import org.joda.time.LocalDate

@Entity(tableName = "goals")
class Goal(var title: String, val recurrence: Recurrence, val type: GoalType) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var dateCreated: LocalDate = LocalDate.now()

    @Embedded
    var timeGoalData: TimeGoalData? = null

    @Embedded
    var numberGoalData: NumberGoalData? = null

}

data class TimeGoalData(var timeTarget: Duration)
data class NumberGoalData(var numberTarget: Int?)


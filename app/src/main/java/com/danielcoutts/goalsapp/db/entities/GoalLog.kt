package com.danielcoutts.goalsapp.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.danielcoutts.goalsapp.db.entities.Goal
import com.danielcoutts.goalsapp.etc.Recurrence
import org.joda.time.LocalDate

@Entity(tableName = "goal_logs",
        indices = [Index(value = ["goalId"])],
        foreignKeys = [
            ForeignKey(entity = Goal::class,
                    parentColumns = ["id"],
                    childColumns = ["goalId"],
                    onDelete = ForeignKey.CASCADE)
        ])
open class GoalLog(val goalId: Long, val recurrence: Recurrence, val date: LocalDate, var numberLogged: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}

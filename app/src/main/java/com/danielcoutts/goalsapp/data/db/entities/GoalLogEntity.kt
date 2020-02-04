package com.danielcoutts.goalsapp.data.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.danielcoutts.goalsapp.data.db.etc.GoalType
import com.danielcoutts.goalsapp.data.models.Recurrence
import java.time.LocalDate

@Entity(tableName = "goal_logs",
        indices = [Index(value = ["goalId"])],
        foreignKeys = [
            ForeignKey(entity = GoalEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["goalId"],
                    onDelete = ForeignKey.CASCADE)
        ])
internal class GoalLogEntity(
        val goalId: Long,
        val recurrence: Recurrence,
        val goalType: GoalType,
        val date: LocalDate,
        var numberLogged: Int
) {

    constructor(
            id: Long,
            goalId: Long,
            recurrence: Recurrence,
            goalType: GoalType,
            date: LocalDate,
            numberLogged: Int
    ) : this(goalId, recurrence, goalType, date, numberLogged) {
        this.id = id
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}

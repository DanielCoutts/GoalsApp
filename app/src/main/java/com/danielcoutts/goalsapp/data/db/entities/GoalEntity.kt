package com.danielcoutts.goalsapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielcoutts.goalsapp.data.db.etc.GoalType
import com.danielcoutts.goalsapp.data.models.Recurrence
import java.time.LocalDate

@Entity(tableName = "goals")
internal class GoalEntity(
        var title: String,
        val recurrence: Recurrence,
        val goalType: GoalType,
        var target: Int
) {

    constructor(
            id: Long,
            title: String,
            recurrence: Recurrence,
            goalType: GoalType,
            target: Int,
            dateCreated: LocalDate
    ) : this(title, recurrence, goalType, target) {
        this.id = id
        this.dateCreated = dateCreated
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var dateCreated: LocalDate = LocalDate.now()

}
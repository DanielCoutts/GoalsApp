package com.danielcoutts.goalsapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielcoutts.goalsapp.data.db.etc.GoalType
import com.danielcoutts.goalsapp.data.models.Recurrence
import java.time.LocalDate

@Entity(tableName = "goals")
internal data class GoalEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val title: String,
        val recurrence: Recurrence,
        val goalType: GoalType,
        var target: Int,
        var dateCreated: LocalDate = LocalDate.now()
)
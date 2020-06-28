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
internal data class GoalLogEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val goalId: Long,
        val recurrence: Recurrence,
        val goalType: GoalType,
        val date: LocalDate,
        val numberLogged: Int
)

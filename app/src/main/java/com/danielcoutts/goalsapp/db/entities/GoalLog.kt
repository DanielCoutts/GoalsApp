package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.danielcoutts.goalsapp.db.entities.Goal
import org.joda.time.LocalDate

@Entity(tableName = "goal_logs",
        indices = [Index(value = ["goalId"])],
        foreignKeys = [
            ForeignKey(entity = Goal::class,
                    parentColumns = ["id"],
                    childColumns = ["goalId"],
                    onDelete = ForeignKey.CASCADE)
        ])
open class GoalLog(val goalId: Long, val date: LocalDate) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
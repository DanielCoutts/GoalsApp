package com.danielcoutts.goalsapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielcoutts.goalsapp.etc.Recurrence
import java.time.LocalDate

@Entity(tableName = "goals")
class Goal(var title: String, val recurrence: Recurrence, var target: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var dateCreated: LocalDate = LocalDate.now()

}


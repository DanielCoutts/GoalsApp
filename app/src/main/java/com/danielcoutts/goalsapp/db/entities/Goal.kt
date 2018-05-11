package com.danielcoutts.goalsapp.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.danielcoutts.goalsapp.etc.Recurrence
import org.joda.time.LocalDate

@Entity(tableName = "goals")
class Goal(var title: String, val recurrence: Recurrence, var target: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var dateCreated: LocalDate = LocalDate.now()

}


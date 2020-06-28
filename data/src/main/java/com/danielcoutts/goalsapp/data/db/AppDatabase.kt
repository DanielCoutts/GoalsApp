package com.danielcoutts.goalsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielcoutts.goalsapp.data.db.daos.GoalDao
import com.danielcoutts.goalsapp.data.db.daos.GoalLogDao
import com.danielcoutts.goalsapp.data.db.entities.*

@Database(
        entities = [
            GoalEntity::class,
            GoalLogEntity::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun goalLogDao(): GoalLogDao
}
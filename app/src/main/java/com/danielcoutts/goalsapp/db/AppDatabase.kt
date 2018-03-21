package com.danielcoutts.goalsapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.danielcoutts.goalsapp.application.MyApplication
import com.danielcoutts.goalsapp.db.daos.GoalDao
import com.danielcoutts.goalsapp.db.entities.*

@Database(
        entities = [
            Goal::class,
            GoalLog::class,
            TimeGoalLog::class,
            NumberGoalLog::class,
            CheckboxGoalLog::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val instance: AppDatabase by lazy {
            Room.databaseBuilder(MyApplication.instance, AppDatabase::class.java, "database").build()
        }
    }

    abstract fun goalDao(): GoalDao

}
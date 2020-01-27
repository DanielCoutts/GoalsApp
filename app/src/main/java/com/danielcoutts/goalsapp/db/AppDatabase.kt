package com.danielcoutts.goalsapp.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielcoutts.goalsapp.application.MyApplication
import com.danielcoutts.goalsapp.db.daos.GoalDao
import com.danielcoutts.goalsapp.db.daos.GoalLogDao
import com.danielcoutts.goalsapp.db.entities.*

@Database(
        entities = [
            Goal::class,
            GoalLog::class
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
    abstract fun goalLogDao(): GoalLogDao

}
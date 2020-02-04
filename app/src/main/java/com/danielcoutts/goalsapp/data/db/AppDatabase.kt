package com.danielcoutts.goalsapp.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielcoutts.goalsapp.application.MyApplication
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

    companion object {
        val instance: AppDatabase by lazy {
            Room.databaseBuilder(MyApplication.instance, AppDatabase::class.java, "database").build()
        }
    }

    abstract fun goalDao(): GoalDao
    abstract fun goalLogDao(): GoalLogDao

}
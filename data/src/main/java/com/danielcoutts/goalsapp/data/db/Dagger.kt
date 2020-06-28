package com.danielcoutts.goalsapp.data.db

import android.content.Context
import androidx.room.Room
import com.danielcoutts.goalsapp.common.ApplicationContext
import com.danielcoutts.goalsapp.data.DataScope
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
internal class DatabaseModule {
    @Provides
    @DataScope
    fun providesGoalDao(database: AppDatabase) =
            database.goalDao()

    @Provides
    @DataScope
    fun providesGoalLogDao(database: AppDatabase) =
            database.goalLogDao()

    @Provides
    @DataScope
    fun providesDatabase(@ApplicationContext context: Context, @DatabaseName databaseName: String): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()

    @Provides
    @DataScope
    @DatabaseName
    fun providesDatabaseName(): String = "database"
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class DatabaseName
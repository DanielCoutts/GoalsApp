package com.danielcoutts.goalsapp.data.repos

import com.danielcoutts.goalsapp.data.DataScope
import com.danielcoutts.goalsapp.data.PrivateToDataModule
import com.danielcoutts.goalsapp.data.db.daos.GoalDao
import com.danielcoutts.goalsapp.data.db.daos.GoalLogDao
import dagger.Module
import dagger.Provides

@Module
internal class RepositoryModule {
    @Provides
    @DataScope
    @PrivateToDataModule
    fun providesGoalRepository(goalDao: GoalDao): GoalRepository =
            GoalRepositoryImpl(goalDao)

    @Provides
    @DataScope
    @PrivateToDataModule
    fun providesGoalLogRepository(goalLogDao: GoalLogDao): GoalLogRepository =
            GoalLogRepositoryImpl(goalLogDao)

}
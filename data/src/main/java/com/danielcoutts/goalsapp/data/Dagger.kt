package com.danielcoutts.goalsapp.data

import com.danielcoutts.goalsapp.data.db.DatabaseModule
import com.danielcoutts.goalsapp.data.repos.GoalLogRepository
import com.danielcoutts.goalsapp.data.repos.GoalRepository
import com.danielcoutts.goalsapp.data.repos.RepositoryModule
import dagger.Subcomponent
import javax.inject.Qualifier
import javax.inject.Scope

@DataScope
@Subcomponent(
        modules = [DatabaseModule::class, RepositoryModule::class]
)
interface DataComponent {
    @DataScope
    @PrivateToDataModule
    fun getGoalRepository(): GoalRepository

    @DataScope
    @PrivateToDataModule
    fun getGoalLogRepository(): GoalLogRepository

    @Subcomponent.Factory
    interface Factory {
        fun create(): DataComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DataScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PrivateToDataModule
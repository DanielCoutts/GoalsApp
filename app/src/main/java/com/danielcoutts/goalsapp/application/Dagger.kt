package com.danielcoutts.goalsapp.application

import android.app.Application
import android.content.Context
import com.danielcoutts.goalsapp.common.ApplicationContext
import com.danielcoutts.goalsapp.data.DataComponent
import com.danielcoutts.goalsapp.data.repos.GoalLogRepository
import com.danielcoutts.goalsapp.data.repos.GoalRepository
import dagger.*
import javax.inject.Scope

@ApplicationScope
@Component(
        modules = [AppModule::class, DataModule::class]
)
interface AppComponent {

    fun getGoalRepository(): GoalRepository
    fun getGoalLogRepository(): GoalLogRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}

@Module
class AppModule {
    @Provides
    @ApplicationScope
    fun provideMyApplication(application: Application): MyApplication =
            application as MyApplication

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideApplicationContext(myApplication: MyApplication): Context =
            myApplication
}

@Module(subcomponents = [DataComponent::class])
class DataModule {
    @Provides
    @ApplicationScope
    fun provideGoalRepository(dataComponent: DataComponent): GoalRepository =
            dataComponent.getGoalRepository()

    @Provides
    @ApplicationScope
    fun provideGoalLogRepository(dataComponent: DataComponent): GoalLogRepository =
            dataComponent.getGoalLogRepository()

    @Provides
    @ApplicationScope
    fun provideDataComponent(factory: DataComponent.Factory): DataComponent =
            factory.create()
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
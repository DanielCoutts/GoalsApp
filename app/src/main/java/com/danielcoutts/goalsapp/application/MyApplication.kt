package com.danielcoutts.goalsapp.application

import android.app.Application

class MyApplication : Application(), AppComponentProvider {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    private lateinit var _appComponent: AppComponent
    override val appComponent: AppComponent
        get() {
            if (!this::_appComponent.isInitialized) {
                _appComponent = DaggerAppComponent
                        .factory()
                        .create(this)
            }
            return _appComponent
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
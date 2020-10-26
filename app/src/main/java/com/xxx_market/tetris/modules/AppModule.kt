package com.xxx_market.tetris.modules

import android.app.Application
import android.content.Context
import com.xxx_market.tetris.model.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Singleton
    @Provides
    fun providesNetworkConnectivityHelper(): AppPreferences {
        return AppPreferences(application.applicationContext)
    }
}
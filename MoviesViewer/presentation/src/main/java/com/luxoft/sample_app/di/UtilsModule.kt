package com.luxoft.sample_app.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    companion object {
        const val APP_PREFERENCES_NAME = "APP_PREFS_NAME"
    }

    @Provides
    @Singleton
    internal fun providesAssetManager(context: Context): AssetManager =
        context.assets

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application) : SharedPreferences {
        return app.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideResources(app: Application) : Resources {
        return app.resources
    }
}
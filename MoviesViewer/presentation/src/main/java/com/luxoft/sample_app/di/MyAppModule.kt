package com.luxoft.sample_app.di

import android.app.Application
import android.content.Context
import com.luxoft.sample_app.MainApplication
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    AndroidSupportInjectionModule::class,
    MyActivityModule::class])
abstract class MyAppModule {

    @Binds
    internal abstract fun application(app: MainApplication): Application

    @Binds
    internal abstract fun applicationContext(app: MainApplication): Context
}

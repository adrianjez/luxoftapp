package com.luxoft.sample_app.di

import com.luxoft.sample_app.presentation.search.MainActivity
import com.luxoft.sample_app.presentation.search.MainActivityModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyActivityModule {

    /**
     * Injector
     */
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun activityInjector(): MainActivity

}

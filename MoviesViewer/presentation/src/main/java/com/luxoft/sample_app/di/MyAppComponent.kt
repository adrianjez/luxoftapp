package com.luxoft.sample_app.di

import com.luxoft.data.movies.MovieServiceModule
import com.luxoft.sample_app.MainApplication
import com.luxoft.sample_app.di.viewmodel.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [MyAppModule::class, UtilsModule::class,
    ViewModelFactoryModule::class, NetworkModule::class, MovieServiceModule::class])
interface MyAppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}

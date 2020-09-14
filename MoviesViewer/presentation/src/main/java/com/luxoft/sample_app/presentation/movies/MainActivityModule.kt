package com.luxoft.sample_app.presentation.movies

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module(includes = [MainActivityProvider::class])
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainActivity(mainActivity: MainActivity): AppCompatActivity

}

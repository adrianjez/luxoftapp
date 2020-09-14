package com.luxoft.sample_app.presentation.movies.popularlist

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class PopularMoviesModule {

    @Binds
    internal abstract fun fragmentPopularMovies(login: PopularMoviesFragment): Fragment

}

package com.luxoft.sample_app.presentation.search

import com.luxoft.sample_app.presentation.search.movie.details.MovieDetailsFragment
import com.luxoft.sample_app.presentation.search.movie.details.MovieDetailsModule
import com.luxoft.sample_app.presentation.search.movie.popularlist.PopularMoviesFragment
import com.luxoft.sample_app.presentation.search.movie.popularlist.PopularMoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @ContributesAndroidInjector(modules = [PopularMoviesModule::class])
    internal abstract fun fragmentPopularMoviesInjector(): PopularMoviesFragment

    @ContributesAndroidInjector(modules = [MovieDetailsModule::class])
    internal abstract fun fragmentMovieDetailsInjector(): MovieDetailsFragment
}
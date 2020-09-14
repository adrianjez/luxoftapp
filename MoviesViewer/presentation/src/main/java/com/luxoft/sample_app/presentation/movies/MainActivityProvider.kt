package com.luxoft.sample_app.presentation.movies

import com.luxoft.sample_app.presentation.movies.moviedetails.MovieDetailsFragment
import com.luxoft.sample_app.presentation.movies.moviedetails.MovieDetailsModule
import com.luxoft.sample_app.presentation.movies.popularlist.PopularMoviesFragment
import com.luxoft.sample_app.presentation.movies.popularlist.PopularMoviesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @ContributesAndroidInjector(modules = [PopularMoviesModule::class])
    internal abstract fun fragmentPopularMoviesInjector(): PopularMoviesFragment

    @ContributesAndroidInjector(modules = [MovieDetailsModule::class])
    internal abstract fun fragmentMovieDetailsInjector(): MovieDetailsFragment
}
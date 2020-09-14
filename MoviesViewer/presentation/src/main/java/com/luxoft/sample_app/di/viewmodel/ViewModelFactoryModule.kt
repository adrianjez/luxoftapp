package com.luxoft.sample_app.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luxoft.sample_app.presentation.movies.moviedetails.MovieDetailsViewModel
import com.luxoft.sample_app.presentation.movies.popularlist.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindsPopularMoviesViewModel(viewModel: PopularMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindsMovieDetailsViewModel(viewModel: MovieDetailsViewModel): ViewModel
}
package com.luxoft.sample_app.presentation.search.movie.details

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailsModule {

    @Binds
    internal abstract fun bindFragment(login: MovieDetailsFragment): Fragment

}

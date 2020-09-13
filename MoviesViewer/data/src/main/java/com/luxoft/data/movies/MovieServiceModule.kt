package com.luxoft.data.movies

import com.luxoft.data.movies.api.MovieAPI
import com.luxoft.data.movies.repository.FavouriteMovieRepositoryImpl
import com.luxoft.data.movies.repository.MovieRepositoryImpl
import com.luxoft.domain.repository.FavouriteMovieRepository
import com.luxoft.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MovieServiceModule {

    /**
     * Methods providing API
     */
    @Provides
    @Singleton
    internal fun provideMovieAPI(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    /**
     * Methods providing repository
     */
    @Provides
    @Singleton
    fun provideMovieRepository(movieRepository: MovieRepositoryImpl): MovieRepository {
        return movieRepository
    }

    @Provides
    @Singleton
    fun provideFavouriteMovieRepository(repo: FavouriteMovieRepositoryImpl): FavouriteMovieRepository {
        return repo
    }
}
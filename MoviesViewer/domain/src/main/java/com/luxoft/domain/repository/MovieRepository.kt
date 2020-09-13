package com.luxoft.domain.repository

import androidx.paging.PagingData
import com.luxoft.domain.model.Movie
import com.luxoft.domain.model.MovieDetails
import com.luxoft.domain.model.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetails(movieID: Long): ResultWrapper<MovieDetails>
    fun getPopularMoviesStream(): Flow<PagingData<Movie>>
    fun getSearchMoviesStream(forQuery: String): Flow<PagingData<Movie>>
}
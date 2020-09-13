package com.luxoft.data.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.luxoft.data.movies.api.MovieAPI
import com.luxoft.data.movies.pagesources.PopularMoviesPagingSource
import com.luxoft.data.movies.pagesources.SearchMoviesPagingQuerySource
import com.luxoft.domain.model.Movie
import com.luxoft.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
        private val movieAPI: MovieAPI
) : BaseRepository(), MovieRepository {

    override suspend fun getMovieDetails(movieID: Long) =
            safeApiCall(Dispatchers.IO) { movieAPI.movieDetails(movieID) }

    override fun getPopularMoviesStream(): Flow<PagingData<Movie>> {
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = 20, prefetchDistance = 10),
                pagingSourceFactory = { PopularMoviesPagingSource(movieAPI) }
        ).flow
    }

    override fun getSearchMoviesStream(forQuery: String): Flow<PagingData<Movie>> {
        return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = 20, prefetchDistance = 10),
                pagingSourceFactory = { SearchMoviesPagingQuerySource(movieAPI, forQuery) }
        ).flow
    }

}
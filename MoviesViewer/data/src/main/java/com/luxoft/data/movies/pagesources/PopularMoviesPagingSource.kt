package com.luxoft.data.movies.pagesources

import androidx.paging.PagingSource
import com.luxoft.data.movies.api.MovieAPI
import com.luxoft.domain.model.Movie

class PopularMoviesPagingSource(
        private val movieAPI: MovieAPI
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = movieAPI.nowPlaying(page)
            val movies = response.results
            LoadResult.Page(
                    data = movies,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
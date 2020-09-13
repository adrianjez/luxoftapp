package com.luxoft.domain.interactor

import com.luxoft.domain.repository.MovieRepository
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val repo: MovieRepository) {

    fun loadPopularMoviesFlow() = repo.getPopularMoviesStream()
    fun searchMoviesFlow(forQuery: String) = repo.getSearchMoviesStream(forQuery)
}
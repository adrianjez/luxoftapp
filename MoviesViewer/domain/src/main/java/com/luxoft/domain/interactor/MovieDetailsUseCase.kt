package com.luxoft.domain.interactor

import com.luxoft.domain.repository.FavouriteMovieRepository
import com.luxoft.domain.repository.MovieRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
        private val repository: MovieRepository,
        private val favouriteMovieRepository: FavouriteMovieRepository
) {

    fun isFavorite(id: Long): Boolean = favouriteMovieRepository.isFavorite(id)
    fun setFavoriteState(id: Long, state: Boolean) = favouriteMovieRepository.setFavoriteState(id, state)
    suspend fun loadMovieDetails(movieID: Long) = repository.getMovieDetails(movieID)
}
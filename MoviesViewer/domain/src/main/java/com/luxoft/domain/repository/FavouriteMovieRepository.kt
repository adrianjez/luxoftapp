package com.luxoft.domain.repository

interface FavouriteMovieRepository {
    fun isFavorite(id: Long) : Boolean
    fun setFavoriteState(id: Long, state: Boolean)
}
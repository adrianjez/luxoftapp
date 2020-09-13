package com.luxoft.data.movies.repository

import android.content.SharedPreferences
import com.luxoft.domain.repository.FavouriteMovieRepository
import javax.inject.Inject

class FavouriteMovieRepositoryImpl @Inject constructor(private val preferences: SharedPreferences)
    : FavouriteMovieRepository {

    override fun isFavorite(id: Long): Boolean = preferences.getBoolean(id.toString(), false)
    override fun setFavoriteState(id: Long, state: Boolean) =
            preferences.edit().putBoolean(id.toString(), state).apply()
}
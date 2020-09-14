package com.luxoft.sample_app.presentation.movies.moviedetails

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.luxoft.domain.interactor.MovieDetailsUseCase
import com.luxoft.domain.model.MovieDetails
import com.luxoft.sample_app.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
        resources: Resources,
        private val useCase: MovieDetailsUseCase
)
    : BaseViewModel(resources) {

    val movieDetails = ObservableField<MovieDetails>()
    val isFavourite = ObservableField<Boolean>()

    fun loadMovieDetails(movieID: Long) {
        isLoadingDisplayed.postValue(true)
        viewModelScope.launch {
            isFavourite.set(useCase.isFavorite(movieID))
            handleErrorIfNeeded(useCase.loadMovieDetails(movieID)) {
                movieDetails.set(it)
            }
        }
    }

    fun changeFavoriteState() {
        movieDetails.get()?.let { movieDetails ->
            isFavourite.get()?.let { isCurrentlyFavourite ->
                isFavourite.set(!isCurrentlyFavourite)
                useCase.setFavoriteState(movieDetails.id, !isCurrentlyFavourite)
            }
        }
    }
}
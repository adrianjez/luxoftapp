package com.luxoft.sample_app.presentation.movies.popularlist

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luxoft.domain.interactor.PopularMoviesUseCase
import com.luxoft.domain.model.Movie
import com.luxoft.sample_app.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
        resources: Resources,
        private val popularMoviesUseCase: PopularMoviesUseCase
) : BaseViewModel(resources) {

    val popularMovies = MutableLiveData<PagingData<Movie>>()
    val searchResultMovies = MutableLiveData<PagingData<Movie>?>(null)

    init {
        viewModelScope.launch {
            popularMoviesUseCase.loadPopularMoviesFlow()
                    .cachedIn(viewModelScope)
                    .collectLatest {
                        popularMovies.postValue(it)
                    }
        }
    }

    fun searchQuery(providedQuery: String) {
        if (providedQuery.isNotEmpty()) {
            viewModelScope.launch {
                popularMoviesUseCase.searchMoviesFlow(forQuery = providedQuery)
                        .cachedIn(viewModelScope)
                        .collectLatest {
                            searchResultMovies.postValue(it)
                        }
            }
        } else {
            searchResultMovies.postValue(null)
        }
    }
}
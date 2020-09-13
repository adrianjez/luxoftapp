package com.luxoft.domain.interactor

import com.luxoft.domain.repository.FavouriteMovieRepository
import com.luxoft.domain.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailsUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var movieDetailsUseCase: MovieDetailsUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var favouriteMovieRepository: FavouriteMovieRepository

    @Before
    fun setUp(){
        movieDetailsUseCase = MovieDetailsUseCase(movieRepository, favouriteMovieRepository)
    }

    @Test
    fun testIsFavourite(){
        movieDetailsUseCase.isFavorite(5L)
        Mockito.verify(favouriteMovieRepository).isFavorite(5L)
        Mockito.verifyNoMoreInteractions(favouriteMovieRepository)
        Mockito.verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun testSetFavoriteState1(){
        movieDetailsUseCase.setFavoriteState(5L, true)
        Mockito.verify(favouriteMovieRepository).setFavoriteState(5L, true)
        Mockito.verifyNoMoreInteractions(favouriteMovieRepository)
        Mockito.verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun testSetFavoriteState2(){
        movieDetailsUseCase.setFavoriteState(6L, false)
        Mockito.verify(favouriteMovieRepository).setFavoriteState(6L, false)
        Mockito.verifyNoMoreInteractions(favouriteMovieRepository)
        Mockito.verifyNoMoreInteractions(movieRepository)
    }


    @Test
    fun testMovieDetails() = runBlocking {
        movieDetailsUseCase.loadMovieDetails(6L)
        Mockito.verify(movieRepository).getMovieDetails(6L)
        Mockito.verifyNoMoreInteractions(favouriteMovieRepository)
        Mockito.verifyNoMoreInteractions(movieRepository)
    }

}
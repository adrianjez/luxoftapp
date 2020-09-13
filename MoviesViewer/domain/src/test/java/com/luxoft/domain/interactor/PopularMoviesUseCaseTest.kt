package com.luxoft.domain.interactor

import com.luxoft.domain.repository.MovieRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesUseCaseTest {

    private lateinit var popularMoviesUseCase: PopularMoviesUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp(){
        popularMoviesUseCase = PopularMoviesUseCase(movieRepository)
    }

    @Test
    fun testPopularMoviesInUseCase(){
        popularMoviesUseCase.loadPopularMoviesFlow()
        Mockito.verify(movieRepository).getPopularMoviesStream()
        Mockito.verifyNoMoreInteractions(movieRepository)
    }

    @Test
    fun testSearchMoviesInUseCase(){
        popularMoviesUseCase.searchMoviesFlow("test")
        Mockito.verify(movieRepository).getSearchMoviesStream("test")
        Mockito.verifyNoMoreInteractions(movieRepository)
    }
}
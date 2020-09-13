package com.luxoft.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luxoft.data.movies.api.MovieAPI
import com.luxoft.data.movies.repository.MovieRepositoryImpl
import com.luxoft.data.utils.ReadJsonFileUtils
import com.luxoft.domain.model.MovieDetails
import com.luxoft.domain.model.ResultWrapper
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryImplTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var movieRepositoryImpl: MovieRepositoryImpl

    private lateinit var testData : MovieDetails

    @Mock
    private lateinit var movieAPI: MovieAPI

    @Before
    fun setUp(){
        movieRepositoryImpl = MovieRepositoryImpl(movieAPI)
        val listType = object : TypeToken<MovieDetails>() {}.type
        testData = Gson().fromJson(ReadJsonFileUtils.loadJsonFileFromResources("movieDetails.json"), listType)
    }

    @Test
    fun testMovieDetails() = runBlocking {
        Mockito.`when`(movieAPI.movieDetails((Matchers.anyLong())))
                .thenReturn(testData)

        val result = movieRepositoryImpl.getMovieDetails(56L)

        Mockito.verify(movieAPI).movieDetails(56L)

        assert(result is ResultWrapper.Success)
        val successData = result as ResultWrapper.Success<MovieDetails>
        assert(successData.value == testData)
    }
}
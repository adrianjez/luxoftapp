package com.luxoft.data.movies.api

import com.luxoft.domain.model.BaseResponse
import com.luxoft.domain.model.Movie
import com.luxoft.domain.model.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("3/movie/now_playing")
    suspend fun nowPlaying(@Query("page") page: Int): BaseResponse<Movie>

    @GET("3/movie/{movieID}")
    suspend fun movieDetails(@Path("movieID") movieID: Long): MovieDetails

    @GET("3/search/movie")
    suspend fun search(
            @Query("page") page: Int,
            @Query("query") query: String): BaseResponse<Movie>

}
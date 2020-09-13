package com.luxoft.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("release_date") val releaseDate: String,
        @field:SerializedName("overview") val overview: String,
        @field:SerializedName("poster_path") val posterPath: String,
        @field:SerializedName("backdrop_path") val backdropPath: String,
        @field:SerializedName("vote_average") val voteAverage: Float
)
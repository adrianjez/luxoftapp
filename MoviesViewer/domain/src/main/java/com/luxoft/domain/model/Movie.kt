package com.luxoft.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
        @field:SerializedName("id") val id: Long,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("poster_path") val posterPath: String
)
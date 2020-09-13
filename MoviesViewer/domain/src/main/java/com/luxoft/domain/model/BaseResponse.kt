package com.luxoft.domain.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
        @field:SerializedName("page") val page: Int,
        @field:SerializedName("total_results") val totalResults: Int,
        @field:SerializedName("total_pages") val totalPages: Int,
        @field:SerializedName("results") val results: ArrayList<T>
)
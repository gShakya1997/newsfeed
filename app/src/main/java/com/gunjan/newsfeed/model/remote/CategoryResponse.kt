package com.gunjan.newsfeed.model.remote

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("status") val status: String,
    @SerializedName("total") val total: String,
    @SerializedName("results") val categories: List<Category>
)
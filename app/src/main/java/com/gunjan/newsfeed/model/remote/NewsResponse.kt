package com.gunjan.newsfeed.model.remote

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("total") val total: String,
    @SerializedName("results") val news: List<News>
)
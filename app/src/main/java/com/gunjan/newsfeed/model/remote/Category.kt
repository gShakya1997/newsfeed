package com.gunjan.newsfeed.model.remote

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("webTitle") val webTitle: String,
    @SerializedName("webUrl") val webUrl: String,
    @SerializedName("apiUrl") val apiUrl: String
)
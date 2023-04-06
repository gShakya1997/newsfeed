package com.gunjan.newsfeed.model.remote

import com.google.gson.annotations.SerializedName

data class SectionResponse(@SerializedName("response") val response: NewsResponse)

package com.gunjan.newsfeed.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    companion object {
        private const val GET_NEWS = "sections"
    }

    @GET(GET_NEWS)
    suspend fun getNews(
        @Query("q") query: String,
        @Query("api-key") apiKey: String
    ): Response<SectionResponse>
}
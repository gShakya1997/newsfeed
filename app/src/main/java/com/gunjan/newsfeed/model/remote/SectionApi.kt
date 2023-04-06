package com.gunjan.newsfeed.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApi {
    companion object {
        private const val GET_SECTION = "sections"
    }

    @GET(GET_SECTION)
    suspend fun getSection(
        @Query("q") category: String,
        @Query("api-key") apiKey: String
    ): Response<SectionResponse>
}
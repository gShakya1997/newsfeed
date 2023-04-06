package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.remote.News

interface NewsRepository {
    suspend fun getNews(query: String, apiKey: String): Resource<List<News>>
}
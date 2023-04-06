package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.remote.Category

interface SectionRepository {
    suspend fun getCategory(query: String, apiKey: String): Resource<List<Category>>
}
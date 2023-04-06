package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.core.utils.SafeApiCall
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.model.remote.Category
import com.gunjan.newsfeed.model.remote.SectionApi
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val api: SectionApi,
    private val dispatcherProvider: DispatcherProvider
) : SectionRepository, SafeApiCall() {
    override suspend fun getCategory(
        query: String,
        apiKey: String
    ): Resource<List<Category>> =
        when (val sectionResponse =
            safeApiCall(dispatcherProvider) { api.getSection(query, apiKey) }) {
            is Resource.Success -> {
                val response = sectionResponse.data
                val result = response!!.body()
                if (response.isSuccessful && result != null) {
                    if (result.response.status == "ok") {
                        Resource.Success(result.response.categories)
                    } else {
                        Resource.Error(
                            result.response.status.let { UiText.DynamicString(value = it) },
                            result.response.status.let { UiText.DynamicString(value = it) })
                    }
                } else {
                    Resource.Error(
                        UiText.DynamicString(response.message()),
                        UiText.DynamicString(response.message())
                    )
                }
            }
            is Resource.Error -> {
                Resource.Error(
                    UiText.StringResource(R.string.unexpected_error),
                    UiText.StringResource(R.string.please_try_again)
                )
            }
        }
}
package com.gunjan.newsfeed.viewmodel

import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.model.remote.Category

sealed class CategoryEvent {
    object Loading : CategoryEvent()
    class Success(val data: List<Category>) : CategoryEvent()
    class Failure(val title: UiText?, val message: UiText) : CategoryEvent()
}
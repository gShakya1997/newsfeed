package com.gunjan.newsfeed.viewmodel

import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.model.remote.News

sealed class NewsEvent {
    object Loading : NewsEvent()
    class Success(val data: List<News>) : NewsEvent()
    class Failure(val title: UiText?, val message: UiText) : NewsEvent()
}
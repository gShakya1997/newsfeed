package com.gunjan.newsfeed.viewmodel

import com.gunjan.newsfeed.core.utils.UiText

sealed class Event {
    object Loading : Event()
    class Success(val data: Any) : Event()
    class Failure(val title: UiText?, val message: UiText) : Event()
}
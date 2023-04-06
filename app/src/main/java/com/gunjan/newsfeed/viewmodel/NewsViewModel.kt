package com.gunjan.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _getNews = MutableSharedFlow<NewsEvent>()
    val getNews: SharedFlow<NewsEvent> = _getNews

    fun getNews(category: String, apiKey: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _getNews.emit(NewsEvent.Loading)
            when (val getNewsResponse = newsRepository.getNews(category, apiKey)) {
                is Resource.Success -> _getNews.emit(NewsEvent.Success(getNewsResponse.data!!))
                is Resource.Error -> _getNews.emit(
                    NewsEvent.Failure(
                        getNewsResponse.errorTitle!!,
                        getNewsResponse.message!!
                    )
                )
            }
        }
    }
}
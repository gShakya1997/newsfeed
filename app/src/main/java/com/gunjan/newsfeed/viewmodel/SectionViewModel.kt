package com.gunjan.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.repo.SectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _getCategory = MutableSharedFlow<CategoryEvent>()
    val getCategory: SharedFlow<CategoryEvent> = _getCategory

    fun getNews(category: String, apiKey: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _getCategory.emit(CategoryEvent.Loading)
            when (val getNewsResponse = sectionRepository.getCategory(category, apiKey)) {
                is Resource.Success -> _getCategory.emit(CategoryEvent.Success(getNewsResponse.data!!))
                is Resource.Error -> _getCategory.emit(
                    CategoryEvent.Failure(
                        getNewsResponse.errorTitle!!,
                        getNewsResponse.message!!
                    )
                )
            }
        }
    }
}
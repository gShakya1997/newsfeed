package com.gunjan.newsfeed.di

import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.model.remote.NewsApi
import com.gunjan.newsfeed.model.repo.NewsRepository
import com.gunjan.newsfeed.model.repo.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object NewsModule {
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRepository(
        api: NewsApi,
        dispatcherProvider: DispatcherProvider
    ): NewsRepository = NewsRepositoryImpl(api, dispatcherProvider)
}
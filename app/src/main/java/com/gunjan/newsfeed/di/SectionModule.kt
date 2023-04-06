package com.gunjan.newsfeed.di

import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.model.remote.SectionApi
import com.gunjan.newsfeed.model.repo.SectionRepository
import com.gunjan.newsfeed.model.repo.SectionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object SectionModule {
    @Provides
    fun provideSectionApi(retrofit: Retrofit): SectionApi = retrofit.create(SectionApi::class.java)

    @Provides
    fun provideNewsRepository(
        api: SectionApi,
        dispatcherProvider: DispatcherProvider
    ): SectionRepository = SectionRepositoryImpl(api, dispatcherProvider)
}
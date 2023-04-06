package com.gunjan.newsfeed.di

import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.model.database.UsersDao
import com.gunjan.newsfeed.model.repo.UserRepositoryImpl
import com.gunjan.newsfeed.model.repo.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UserModule {
    @Provides
    fun provideUsersRepository(
        usersDao: UsersDao,
        dispatcherProvider: DispatcherProvider
    ): UsersRepository = UserRepositoryImpl(usersDao, dispatcherProvider)
}
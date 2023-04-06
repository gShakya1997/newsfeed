package com.gunjan.newsfeed.model.repo

import android.util.Log
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.core.utils.SafeApiCall
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.model.database.Users
import com.gunjan.newsfeed.model.database.UsersDao
import javax.inject.Inject

private const val TAG = "UserRepositoryImpl"

class UserRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val dispatcherProvider: DispatcherProvider
) : UsersRepository, SafeApiCall() {
    override suspend fun registerUser(users: Users): Resource<Long> =
        when (val registerUserResponse = safeApiCall(dispatcherProvider) {
            usersDao.registerUser(users)
        }) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(registerUserResponse.data!!)
            is Resource.Error -> {
                Resource.Error(
                    UiText.StringResource(R.string.unexpected_error),
                    UiText.StringResource(R.string.please_try_again)
                )
            }
        }

    override suspend fun checkUserEmail(email: String): Resource<Boolean> {
        Log.e(TAG, "checkUserEmail: Launch ${usersDao.checkUserEmail(email)}")
        return when (val checkEmailResponse =
            safeApiCall(dispatcherProvider) { usersDao.checkUserEmail(email) }) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> {
                Log.e(TAG, "checkUserEmail: Success ${checkEmailResponse.data}", )
                if (checkEmailResponse.data == null) {
                    Resource.Success(true)
                } else {
                    Resource.Success(false)
                }
            }
            is Resource.Error -> {
                Log.e(TAG, "checkUserEmail: Error ${checkEmailResponse.message}")
                Resource.Error(
                    UiText.StringResource(R.string.unexpected_error),
                    UiText.StringResource(R.string.please_try_again)
                )
            }
        }
    }

    override suspend fun login(email: String, password: String): Resource<Users> =
        when (val loginResponse =
            safeApiCall(dispatcherProvider) { usersDao.checkUserEmail(email) }) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(loginResponse.data!!)
            is Resource.Error -> {
                Resource.Error(
                    UiText.StringResource(R.string.unexpected_error),
                    UiText.StringResource(R.string.please_try_again)
                )
            }
        }

    override suspend fun getUserDetail(email: String): Resource<Users> =
        when (val getUserDetailResponse =
            safeApiCall(dispatcherProvider) { usersDao.checkUserEmail(email) }) {
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(getUserDetailResponse.data!!)
            is Resource.Error -> {
                Resource.Error(
                    UiText.StringResource(R.string.unexpected_error),
                    UiText.StringResource(R.string.please_try_again)
                )
            }
        }
}
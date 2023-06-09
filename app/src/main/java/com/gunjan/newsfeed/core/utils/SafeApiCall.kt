package com.gunjan.newsfeed.core.utils

import com.gunjan.newsfeed.R
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class SafeApiCall {
    suspend fun <T> safeApiCall(
        dispatcherProvider: DispatcherProvider,
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(dispatcherProvider.io) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            404 -> {
                                Resource.Error(
                                    errorTitle = UiText.StringResource(resId = R.string.service_unavailable),
                                    message = UiText.StringResource(resId = R.string.unable_to_find_service)
                                )
                            }
                            in 500..599 -> {
                                Resource.Error(
                                    errorTitle = UiText.StringResource(resId = R.string.server_error),
                                    message = UiText.StringResource(resId = R.string.try_again_later)
                                )
                            }
                            else -> {
                                Resource.Error(
                                    errorTitle = UiText.StringResource(resId = R.string.unknown_error_title),
                                    message = UiText.StringResource(resId = R.string.unknown_error)
                                )
                            }
                        }
                    }

                    is ConnectException -> Resource.Error(
                        errorTitle = UiText.StringResource(resId = R.string.service_unavailable),
                        message = UiText.StringResource(resId = R.string.unable_to_find_service)
                    )

                    is SocketTimeoutException, is TimeoutException -> Resource.Error(
                        errorTitle = UiText.StringResource(resId = R.string.something_went_wrong),
                        message = UiText.StringResource(resId = R.string.try_again)
                    )

                    is EOFException, is NullPointerException -> Resource.Error(
                        errorTitle = UiText.StringResource(resId = R.string.server_error),
                        message = UiText.StringResource(resId = R.string.try_again_later)
                    )

                    is UnknownHostException, is IOException -> Resource.Error(
                        errorTitle = UiText.StringResource(resId = R.string.network_error),
                        message = UiText.StringResource(resId = R.string.check_connectivity)
                    )

                    else -> Resource.Error(
                        errorTitle = UiText.StringResource(resId = R.string.something_went_wrong),
                        message = UiText.StringResource(resId = R.string.try_again)
                    )
                }
            }
        }
    }
}
package com.gunjan.newsfeed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gunjan.newsfeed.R
import com.gunjan.newsfeed.core.utils.DispatcherProvider
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.core.utils.UiText
import com.gunjan.newsfeed.model.database.Users
import com.gunjan.newsfeed.model.repo.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var usersRepository: UsersRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _checkUserEmail = MutableSharedFlow<Event>()
    val checkUserEmail: SharedFlow<Event> = _checkUserEmail

    private val _registerUser = MutableSharedFlow<Event>()
    val registerUser: SharedFlow<Event> = _registerUser

    private val _login = MutableSharedFlow<Event>()
    val login: SharedFlow<Event> = _login

    private val _getUserDetail = MutableLiveData<Resource<Users>>()
    val getUserDetail: LiveData<Resource<Users>> = _getUserDetail

    fun checkExistUsers(email: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _checkUserEmail.emit(Event.Loading)
            when (val checkExistUsers = usersRepository.checkUserEmail(email)) {
                is Resource.Success -> {
                    val isSuccess = checkExistUsers.data!!
                    if (isSuccess) _checkUserEmail.emit(Event.Success(checkExistUsers.data))
                    else _checkUserEmail.emit(
                        Event.Failure(
                            null,
                            UiText.StringResource(R.string.email_already_used)
                        )
                    )
                }
                is Resource.Error -> _checkUserEmail.emit(
                    Event.Failure(
                        checkExistUsers.errorTitle!!,
                        checkExistUsers.message!!
                    )
                )
            }
        }
    }

    fun registerUser(user: Users) {
        viewModelScope.launch(dispatcherProvider.io) {
            _registerUser.emit(Event.Loading)
            when (val registerResponse = usersRepository.registerUser(user)) {
                is Resource.Success -> _registerUser.emit(Event.Success(registerResponse.data!!))
                is Resource.Error -> _registerUser.emit(
                    Event.Failure(
                        registerResponse.errorTitle!!,
                        registerResponse.message!!
                    )
                )
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            when (val loginResponse = usersRepository.login(email, password)) {
                is Resource.Success -> _login.emit(Event.Success(loginResponse.data!!))
                is Resource.Error -> _login.emit(
                    Event.Failure(
                        loginResponse.errorTitle!!,
                        loginResponse.message!!
                    )
                )
            }
        }
    }
}
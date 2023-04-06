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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var usersRepository: UsersRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {
    private val _registerUser = MutableLiveData<Resource<Long>>()
    val registerUser: LiveData<Resource<Long>> = _registerUser

    private val _checkUserEmail = MutableLiveData<Resource<Boolean>>()
    val checkUserEmail: LiveData<Resource<Boolean>> = _checkUserEmail

    private val _login = MutableLiveData<Resource<Users>>()
    val login: LiveData<Resource<Users>> = _login

    private val _getUserDetail = MutableLiveData<Resource<Users>>()
    val getUserDetail: LiveData<Resource<Users>> = _getUserDetail

    fun registerUser(user: Users) {
        viewModelScope.launch(dispatcherProvider.io) {
            when (val registerResponse = usersRepository.registerUser(user)) {
                is Resource.Loading -> _registerUser.postValue(Resource.Loading())
                is Resource.Success -> _registerUser.postValue(Resource.Success(registerResponse.data!!))
                is Resource.Error -> _registerUser.postValue(
                    Resource.Error(
                        UiText.StringResource(R.string.unexpected_error),
                        UiText.StringResource(R.string.please_try_again)
                    )
                )
            }
        }
    }

    fun checkExistUsers(email: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            when (val checkExistUsers = usersRepository.checkUserEmail(email)) {
                is Resource.Loading -> _checkUserEmail.postValue(Resource.Loading())
                is Resource.Success -> _checkUserEmail.postValue(Resource.Success(checkExistUsers.data!!))
                is Resource.Error -> _checkUserEmail.postValue(
                    Resource.Error(
                        UiText.StringResource(R.string.unexpected_error),
                        UiText.StringResource(R.string.please_try_again)
                    )
                )
            }
        }
    }
}
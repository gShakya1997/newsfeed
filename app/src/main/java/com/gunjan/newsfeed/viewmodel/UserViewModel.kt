package com.gunjan.newsfeed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.repo.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var usersRepository: UsersRepository) :
    ViewModel() {
    private val _registerUser = MutableLiveData<Resource<Long>>()
    val registerUser: LiveData<Resource<Long>> = _registerUser

    private val _checkUserEmail = MutableLiveData<Resource<Long>>()
    val checkUserEmail: LiveData<Resource<Long>> = _checkUserEmail

    private val _login = MutableLiveData<Resource<Long>>()
    val login: LiveData<Resource<Long>> = _login

    private val _getUserDetail = MutableLiveData<Resource<Long>>()
    val getUserDetail: LiveData<Resource<Long>> = _getUserDetail
}
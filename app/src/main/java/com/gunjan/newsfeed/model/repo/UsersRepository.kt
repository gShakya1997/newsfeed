package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.core.utils.Resource
import com.gunjan.newsfeed.model.database.Users

interface UsersRepository {
    suspend fun registerUser(users: Users): Resource<Long>
    suspend fun checkUserEmail(email: String): Resource<Boolean>
    suspend fun login(email: String, password: String): Resource<Users>
    suspend fun getUserDetail(email: String): Resource<Users>
}
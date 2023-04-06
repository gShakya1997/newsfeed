package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.model.database.Users

interface UsersRepository {
    fun registerUser(users: Users): Long
    fun checkUserEmail(email: String): Users
    fun login(email: String, password: String): Users
    fun getUserDetail(email: String): Users
}
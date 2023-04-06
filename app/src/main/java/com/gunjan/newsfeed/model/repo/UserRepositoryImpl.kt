package com.gunjan.newsfeed.model.repo

import com.gunjan.newsfeed.model.database.Users
import com.gunjan.newsfeed.model.database.UsersDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usersDao: UsersDao) : UsersRepository {
    override fun registerUser(users: Users): Long = usersDao.registerUser(users)

    override fun checkUserEmail(email: String): Users = usersDao.checkUserEmail(email)

    override fun login(email: String, password: String): Users = usersDao.login(email, password)

    override fun getUserDetail(email: String): Users = usersDao.getUserDetail(email)
}
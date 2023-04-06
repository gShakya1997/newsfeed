package com.gunjan.newsfeed.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun registerUser(users: Users): Long

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    fun checkUserEmail(email: String): Users

    @Query("SELECT * FROM Users WHERE email LIKE :email AND password LIKE :password")
    fun login(email: String, password: String): Users

    @Query("SELECT * from Users where email LIKE :email")
    fun getUserDetail(email: String): Users
}
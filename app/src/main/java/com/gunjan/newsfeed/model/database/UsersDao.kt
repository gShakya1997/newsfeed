package com.gunjan.newsfeed.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(users: Users): Long

    @Query("SELECT * FROM Users WHERE email LIKE :email")
    suspend fun checkUserEmail(email: String): Users?

    @Query("SELECT * FROM Users WHERE email LIKE :email AND password LIKE :password")
    suspend fun login(email: String, password: String): Users
}
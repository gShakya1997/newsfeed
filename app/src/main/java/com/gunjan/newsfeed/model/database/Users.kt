package com.gunjan.newsfeed.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gunjan.newsfeed.core.Constant.USER_TABLE

@Entity(tableName = USER_TABLE)
data class Users(
    @PrimaryKey val email: String? = "",
    val fullName: String? = "",
    val phone: String? = "",
    val address: String? = "",
    val password: String? = ""
)

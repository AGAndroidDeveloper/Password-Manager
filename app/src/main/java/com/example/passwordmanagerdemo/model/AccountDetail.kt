package com.example.passwordmanagerdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_detail")
data class AccountDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val accountName: String = "",
    val userName: String = "",
    val password: String = "",
    val accountErr: Boolean = false,
    val userNameErr :Boolean = false,
    val passwordErr :Boolean = false
)

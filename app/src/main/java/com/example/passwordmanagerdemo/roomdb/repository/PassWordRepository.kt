package com.example.passwordmanagerdemo.roomdb.repository

import com.example.passwordmanagerdemo.model.AccountDetail
import kotlinx.coroutines.flow.Flow


interface AccountDetailRepository {

    fun getAllAccount(): Flow<List<AccountDetail>>

    fun getItemStream(id: Int): Flow<AccountDetail?>

    suspend fun insertAccountDetail(item: AccountDetail)

    suspend fun deleteItem(item: AccountDetail)

    suspend fun updateItem(item: AccountDetail)
}
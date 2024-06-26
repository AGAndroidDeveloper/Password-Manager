package com.example.passwordmanagerdemo.roomdb.repository

import com.example.passwordmanagerdemo.model.AccountDetail
import com.example.passwordmanagerdemo.roomdb.AccountDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class AccountRepositoryImpl(private val itemDao : AccountDao) : AccountDetailRepository {
    override fun getAllAccount(): Flow<List<AccountDetail>> {
        return itemDao.getAllAccount()
    }

    override fun getItemStream(id: Int): Flow<AccountDetail?> {
        return itemDao.getItemStream(id)
    }

    override suspend fun insertAccountDetail(item: AccountDetail) {
        itemDao.insertAccountDetail(item)
    }

    override suspend fun deleteItem(item: AccountDetail) {
        itemDao.deleteItem(item)
    }

    override suspend fun updateItem(item: AccountDetail) {
        itemDao.updateItem(item)
    }

}
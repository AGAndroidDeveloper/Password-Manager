package com.example.passwordmanagerdemo.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.passwordmanagerdemo.model.AccountDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM account_detail")
    fun getAllAccount(): Flow<List<AccountDetail>>

    @Query("SELECT * FROM account_detail WHERE id = :id")
    fun getItemStream(id: Int): Flow<AccountDetail?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccountDetail(item: AccountDetail)

    @Delete
    suspend fun deleteItem(item: AccountDetail)

    @Update
    suspend fun updateItem(item: AccountDetail)

}
package com.example.passwordmanagerdemo.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passwordmanagerdemo.model.AccountDetail


@Database(entities = [AccountDetail::class,], version = 1)
abstract class AccountRoomDb  : RoomDatabase() {

    abstract fun itemDao(): AccountDao

    companion object {
        @Volatile
        private var Instance: AccountRoomDb? = null

        fun getDatabase(context: Context): AccountRoomDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AccountRoomDb::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
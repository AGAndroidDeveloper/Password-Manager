package com.example.passwordmanagerdemo.di

import android.content.Context
import androidx.room.Room
import com.example.passwordmanagerdemo.roomdb.AccountRoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

//    @Provides
//    fun provideDataBaseInstance(
//        @ApplicationContext
//        context: Context,
//    ) = Room.databaseBuilder(
//        context, AccountRoomDb::class.java, "Account DataBase"
//    ).build()
}

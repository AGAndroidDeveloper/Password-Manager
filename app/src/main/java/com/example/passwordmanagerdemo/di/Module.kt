package com.example.passwordmanagerdemo.di

import android.content.Context
import com.example.passwordmanagerdemo.roomdb.AccountDao
import com.example.passwordmanagerdemo.roomdb.AccountRoomDb
import com.example.passwordmanagerdemo.roomdb.repository.AccountDetailRepository
import com.example.passwordmanagerdemo.roomdb.repository.AccountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Module {


    @Provides
    @Singleton
    fun provideAccountDao(@ApplicationContext context: Context): AccountDao {
        return AccountRoomDb.getDatabase(context).itemDao()
    }


    @Provides
    @Singleton
    fun provideRepository(dao: AccountDao): AccountDetailRepository {
        return AccountRepositoryImpl(dao)
    }


}
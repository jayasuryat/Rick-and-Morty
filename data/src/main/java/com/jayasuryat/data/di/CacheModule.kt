package com.jayasuryat.data.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.data.data.local.impl.CacheClient
import com.jayasuryat.data.data.local.impl.CacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CacheModule {

    @Singleton
    @Provides
    internal fun providesCacheClient(
        @ApplicationContext context: Context,
    ): CacheClient = Room
        .databaseBuilder(context, CacheClient::class.java, CacheClient.DB_NAME)
        .build()

    @Singleton
    @Provides
    internal fun providesCacheDao(
        cacheClient: CacheClient
    ): CacheDao = cacheClient.cacheDao()
}
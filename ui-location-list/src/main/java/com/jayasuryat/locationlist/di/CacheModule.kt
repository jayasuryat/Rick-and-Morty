package com.jayasuryat.locationlist.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.locationlist.data.sources.local.definitions.LocationListLocalDataSource
import com.jayasuryat.locationlist.data.sources.local.impl.LocationListDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object CacheModule {

    @Provides
    @Singleton
    fun providesLocationListDb(
        @ApplicationContext context: Context,
    ): LocationListDb = Room
        .databaseBuilder(context, LocationListDb::class.java, LocationListDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesLocationListLocalDataSource(
        db: LocationListDb,
    ): LocationListLocalDataSource = db.locationListDao()
}
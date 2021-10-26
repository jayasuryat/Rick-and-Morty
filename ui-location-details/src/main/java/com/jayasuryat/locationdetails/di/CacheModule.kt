package com.jayasuryat.locationdetails.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.locationdetails.data.sources.local.definitions.LocationDetailsLocalDataSource
import com.jayasuryat.locationdetails.data.sources.local.impl.LocationDetailsDb
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
    fun providesLocationDetailsDb(
        @ApplicationContext context: Context,
    ): LocationDetailsDb = Room
        .databaseBuilder(context, LocationDetailsDb::class.java, LocationDetailsDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesLocationDetailsLocalDataSource(
        db: LocationDetailsDb,
    ): LocationDetailsLocalDataSource = db.locationDetailsDao()
}
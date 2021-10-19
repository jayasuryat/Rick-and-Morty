package com.jayasuryat.episodedetails.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.episodedetails.data.sources.local.definitions.EpisodeDetailsLocalDataSource
import com.jayasuryat.episodedetails.data.sources.local.impl.EpisodeDetailsDb
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
    fun providesEpisodeDetailsDb(
        @ApplicationContext context: Context,
    ): EpisodeDetailsDb = Room
        .databaseBuilder(context, EpisodeDetailsDb::class.java, EpisodeDetailsDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesCharacterListLocalDataSource(
        db: EpisodeDetailsDb,
    ): EpisodeDetailsLocalDataSource = db.episodeDetailsDao()
}
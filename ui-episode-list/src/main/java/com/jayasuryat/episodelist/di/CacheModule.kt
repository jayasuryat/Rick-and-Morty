package com.jayasuryat.episodelist.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.episodelist.data.sources.local.definitions.EpisodeListLocalDataSource
import com.jayasuryat.episodelist.data.sources.local.impls.EpisodeListDb
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
    fun providesEpisodeListDb(
        @ApplicationContext context: Context,
    ): EpisodeListDb = Room
        .databaseBuilder(context, EpisodeListDb::class.java, EpisodeListDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesEpisodeListLocalDataSource(
        db: EpisodeListDb,
    ): EpisodeListLocalDataSource = db.episodeListDao()
}
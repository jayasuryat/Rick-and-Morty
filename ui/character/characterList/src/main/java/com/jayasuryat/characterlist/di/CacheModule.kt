package com.jayasuryat.characterlist.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.characterlist.data.sources.local.definitions.CharacterListLocalDataSource
import com.jayasuryat.characterlist.data.sources.local.impl.CharacterListDb
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
    fun providesCharactersListDb(
        @ApplicationContext context: Context,
    ): CharacterListDb = Room
        .databaseBuilder(context, CharacterListDb::class.java, CharacterListDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesCharacterListLocalDataSource(
        db: CharacterListDb,
    ): CharacterListLocalDataSource = db.characterListDao()
}
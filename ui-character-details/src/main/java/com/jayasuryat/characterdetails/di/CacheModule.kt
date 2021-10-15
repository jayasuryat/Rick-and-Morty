package com.jayasuryat.characterdetails.di

import android.content.Context
import androidx.room.Room
import com.jayasuryat.characterdetails.data.sources.local.definitons.CharacterDetailsLocalDataSource
import com.jayasuryat.characterdetails.data.sources.local.impl.CharacterDetailsDb
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
    ): CharacterDetailsDb = Room
        .databaseBuilder(context, CharacterDetailsDb::class.java, CharacterDetailsDb.DB_NAME)
        .build()

    @Provides
    @Singleton
    fun providesCharacterDetailsLocalDataSource(
        db: CharacterDetailsDb,
    ): CharacterDetailsLocalDataSource = db.getCharacterDetailsDao()
}
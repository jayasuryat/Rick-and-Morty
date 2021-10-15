package com.jayasuryat.characterlist.di

import com.jayasuryat.basedata.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider {

        return object : DispatcherProvider {
            override fun ui(): CoroutineDispatcher = Dispatchers.Main.immediate
            override fun io(): CoroutineDispatcher = Dispatchers.IO
            override fun computation(): CoroutineDispatcher = Dispatchers.Default
        }
    }
}
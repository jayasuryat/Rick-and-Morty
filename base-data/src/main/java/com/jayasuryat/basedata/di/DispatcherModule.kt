package com.jayasuryat.basedata.di

import com.jayasuryat.basedata.providers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {

    @Provides
    @Singleton
    internal fun providesDispatcherProvider(): DispatcherProvider {

        return object : DispatcherProvider {
            override fun ui(): CoroutineDispatcher = Dispatchers.Main.immediate
            override fun io(): CoroutineDispatcher = Dispatchers.IO
            override fun computation(): CoroutineDispatcher = Dispatchers.Default
        }
    }
}
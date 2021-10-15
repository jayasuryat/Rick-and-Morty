package com.jayasuryat.basedata.di

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.jayasuryat.basedata.Constants
import com.jayasuryat.basedata.providers.GraphQlClientProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object GraphQlModule {

    @Provides
    @Singleton
    internal fun providesGraphQlClient(): GraphQlClientProvider {

        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        val apolloClient = ApolloClient.builder()
            .serverUrl(Constants.BASE_URL)
            .okHttpClient(okHttpClient)
            .build()


        return GraphQlClientProvider(apolloClient)
    }
}
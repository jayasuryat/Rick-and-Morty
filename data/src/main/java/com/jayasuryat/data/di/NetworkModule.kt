package com.jayasuryat.data.di

import com.jayasuryat.data.data.remote.HttpClientProvider
import com.jayasuryat.data.data.remote.impl.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import okhttp3.ConnectionPool
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    internal fun providesHttpClientProvider(): HttpClientProvider = HttpClientProvider {

        HttpClient(OkHttp) {

            engine {
                clientCacheSize = 0
                config {
                    connectionPool(ConnectionPool(5, 10, TimeUnit.SECONDS))
                }
            }

            install(Logging) {
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10L * 1000L
                connectTimeoutMillis = 5L * 1000L
                socketTimeoutMillis = 30L * 1000L
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Singleton
    @Provides
    internal fun providesNetworkClient(
        httpClient: HttpClientProvider
    ): NetworkClient = NetworkClient(httpClient.getClient())
}
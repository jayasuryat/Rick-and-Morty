package com.jayasuryat.data.data.remote

import io.ktor.client.*

internal fun interface HttpClientProvider {
    fun getClient(): HttpClient
}
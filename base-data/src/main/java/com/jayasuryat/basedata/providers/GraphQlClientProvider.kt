package com.jayasuryat.basedata.providers

import com.apollographql.apollo.ApolloClient

public class GraphQlClientProvider internal constructor(
    private val client: ApolloClient,
) {

    public fun getClient(): ApolloClient = client
}
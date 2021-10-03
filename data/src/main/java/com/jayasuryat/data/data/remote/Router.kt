package com.jayasuryat.data.data.remote

import io.ktor.client.request.*
import io.ktor.http.*


internal object Router {

    private fun base(): String = "https://rickandmortyapi.com/api/"

    // https://rickandmortyapi.com/api/character/?page=2
    // https://rickandmortyapi.com/api/location?page=2
    // https://rickandmortyapi.com/api/episode?page=2

    fun characters(page: Int): HttpRequestBuilder.() -> Unit = {
        url.takeFrom(base() + "character".paged(page))
    }

    fun characters(ids: List<Long>): HttpRequestBuilder.() -> Unit = {
        url.takeFrom(base() + "character/" + ids.joinToString())
    }

    fun locations(page: Int): HttpRequestBuilder.() -> Unit = {
        url.takeFrom(base() + "location".paged(page))
    }

    fun episodes(page: Int = 1): HttpRequestBuilder.() -> Unit = {
        url.takeFrom(base() + "episode".paged(page))
    }

    fun episodes(episodes: List<Long>): HttpRequestBuilder.() -> Unit = {
        url.takeFrom(base() + "episode/" + episodes.joinToString())
    }

    private fun String.paged(page: Int): String =
        if (this.last() != '/') "$this/?page=$page" else "$this?page=$page"
}
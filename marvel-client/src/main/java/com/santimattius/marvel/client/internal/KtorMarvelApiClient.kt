package com.santimattius.marvel.client.internal

import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.client.models.ApiResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

internal class KtorMarvelApiClient(private val client: HttpClient) : MarvelApiClient {

    override suspend fun getCharacters(offset: Long, limit: Long): ApiResponse {
        val urlBuilder =
            URLBuilder("https://gateway.marvel.com/v1/public/characters?offset=$offset&limit=$limit")
        return client.get(url = Url(urlBuilder))
    }
}
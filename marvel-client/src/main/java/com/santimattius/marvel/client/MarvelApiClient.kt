package com.santimattius.marvel.client

import com.santimattius.marvel.client.models.ApiResponse

interface MarvelApiClient {
    suspend fun getCharacters(offset: Long, limit: Long): ApiResponse
}
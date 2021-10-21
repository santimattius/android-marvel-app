package com.santimattius.marvel.client

import com.santimattius.marvel.client.models.ApiResponse
import com.santimattius.marvel.client.models.Character

interface MarvelApiClient {
    suspend fun getCharacters(offset: Long, limit: Long): ApiResponse
    suspend fun findCharacter(id: Long): Character
}
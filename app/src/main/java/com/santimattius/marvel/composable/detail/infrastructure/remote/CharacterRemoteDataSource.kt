package com.santimattius.marvel.composable.detail.infrastructure.remote

import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.client.models.Character
import com.santimattius.marvel.composable.detail.infrastructure.CharacterDataSource

class CharacterRemoteDataSource(
    private val apiClient: MarvelApiClient
) : CharacterDataSource {

    override suspend fun findCharacter(id: Long): Character {
        return apiClient.findCharacter(id)
    }
}
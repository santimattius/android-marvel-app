package com.santimattius.marvel.composable.detail.infrastructure

import com.santimattius.marvel.client.models.Character

interface CharacterDataSource {

    suspend fun findCharacter(id: Long): Character
}

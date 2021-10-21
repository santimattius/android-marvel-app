package com.santimattius.marvel.composable.detail.domain

interface CharacterRepository {
    suspend fun findCharacter(id: Long): Character
}
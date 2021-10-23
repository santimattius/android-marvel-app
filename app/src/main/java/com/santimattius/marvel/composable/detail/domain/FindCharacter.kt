package com.santimattius.marvel.composable.detail.domain

class FindCharacter(private val repository: CharacterRepository) {
    suspend operator fun invoke(id: Long) = repository.findCharacter(id)
}
package com.santimattius.marvel.composable.home.domain

class GetCharacters(private val repository: CharactersRepository) {

    operator fun invoke() = repository.getCharacters()
}
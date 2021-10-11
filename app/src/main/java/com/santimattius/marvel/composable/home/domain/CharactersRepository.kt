package com.santimattius.marvel.composable.home.domain

import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<Characters>
}
package com.santimattius.marvel.composable.home.infrastructure

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.composable.home.domain.Characters
import com.santimattius.marvel.composable.home.domain.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(private val apiClient: MarvelApiClient) :
    CharactersRepository {

    private val characters: Flow<Characters> = Pager(PagingConfig(pageSize = 20)) {
        CharactersDataSources(marvelApiClient = apiClient)
    }.flow

    override fun getCharacters(): Flow<Characters> = characters
}
package com.santimattius.marvel.composable.home.infrastructure

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.client.models.asString
import com.santimattius.marvel.composable.home.domain.Character
import com.santimattius.marvel.client.models.Character as CharacterModel

class CharactersDataSources(
    private val marvelApiClient: MarvelApiClient
) : PagingSource<Long, Character>() {

    override fun getRefreshKey(state: PagingState<Long, Character>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Character> {
        return try {
            val nextPage = params.key ?: 1
            val response = marvelApiClient.getCharacters(
                offset = nextPage,
                limit = params.loadSize.toLong()
            )

            val count = response.data.count
            val limit = response.data.limit

            LoadResult.Page(
                data = response.data.results.map { it.asCharacter() },
                prevKey = if (nextPage == 1L) null else nextPage - limit,
                nextKey = if (count < limit) null else nextPage + limit,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

private fun CharacterModel.asCharacter() = object : Character {
    override val id: Long
        get() = this@asCharacter.id
    override val name: String
        get() = this@asCharacter.name
    override val thumbnail: String
        get() = this@asCharacter.thumbnail.asString()
}

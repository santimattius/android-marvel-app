package com.santimattius.marvel.composable.detail.infrastructure

import com.santimattius.marvel.client.models.asString
import com.santimattius.marvel.composable.detail.domain.*
import com.santimattius.marvel.client.models.Character as NetworkCharacter
import com.santimattius.marvel.client.models.ComicsItem as NetworkComic

class CharacterRepositoryImpl(
    private val characterDataSource: CharacterDataSource
) : CharacterRepository {

    override suspend fun findCharacter(id: Long): Character {
        val findCharacter = characterDataSource.findCharacter(id)
        return findCharacter.asCharacter()
    }
}

private fun NetworkCharacter.asCharacter(): Character {
    return object : Character {
        override val id: Long
            get() = this@asCharacter.id
        override val name: String
            get() = this@asCharacter.name
        override val thumbnail: String
            get() = this@asCharacter.thumbnail.asString()
        override val comics: List<Item>
            get() = this@asCharacter.comics.items.asItem()
        override val series: List<Item>
            get() = this@asCharacter.series.items.asItem()
        override val stories: List<Item>
            get() = this@asCharacter.series.items.asItem()
    }
}

private fun List<NetworkComic>.asItem(): List<Item> {
    return this.map {
        object : Item {
            override val name: String
                get() = it.name
        }
    }
}

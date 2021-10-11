package com.santimattius.marvel.client.models// To parse the JSON, install kotlin's serialization plugin and do:

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

@Serializable
data class Data(
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Comics,
    val stories: Stories,
    val events: Comics,
    val urls: List<URL>
)

@Serializable
data class Comics(
    val available: Long,
    val collectionURI: String,
    val items: List<ComicsItem>,
    val returned: Long
)

@Serializable
data class ComicsItem(
    val resourceURI: String,
    val name: String
)

@Serializable
data class Stories(
    val available: Long,
    val collectionURI: String,
    val items: List<StoriesItem>,
    val returned: Long
)

@Serializable
data class StoriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
)


@Serializable
data class Thumbnail(
    val path: String,
    val extension: String
)

fun Thumbnail.asString() = "$path.$extension".replace("http", "https")

@Serializable
data class URL(
    val type: String,
    val url: String
)
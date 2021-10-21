package com.santimattius.marvel.client.internal

import com.santimattius.marvel.marvel.client.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.util.*

private const val TIME_OUT = 60_000
private const val API_KEY = "apikey"
private const val TS = "ts"
private const val HASH = "hash"

internal val ktorHttpClient = HttpClient(Android) {

    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }
    val date = Date().time
    val hash = generateHash(date, BuildConfig.PRIVATE_KEY, BuildConfig.PUBLIC_KEY)

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        parameter(API_KEY, BuildConfig.PUBLIC_KEY)
        parameter(TS, date.toString())
        parameter(HASH, hash)
    }
}
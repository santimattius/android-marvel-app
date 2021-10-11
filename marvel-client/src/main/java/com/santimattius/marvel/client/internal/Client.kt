package com.santimattius.marvel.client.internal

import android.util.Log
import com.santimattius.marvel.marvel.client.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.util.*

private const val TIME_OUT = 60_000

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

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }

        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    val date = Date().time
    val hash = generateHash(date, BuildConfig.PRIVATE_KEY, BuildConfig.PUBLIC_KEY)

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
        parameter("apikey", BuildConfig.PUBLIC_KEY)
        parameter("ts", date.toString())
        parameter("hash", hash)
    }
}
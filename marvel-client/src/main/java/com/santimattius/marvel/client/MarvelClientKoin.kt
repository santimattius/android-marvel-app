package com.santimattius.marvel.client

import com.santimattius.marvel.client.internal.KtorMarvelApiClient
import com.santimattius.marvel.client.internal.ktorHttpClient
import org.koin.dsl.module

object MarvelClientKoin {

    val modules = module {
        single<MarvelApiClient> { KtorMarvelApiClient(ktorHttpClient) }
    }
}
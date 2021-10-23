package com.santimattius.marvel.client

import com.santimattius.marvel.client.internal.MarvelKtorApiClient
import com.santimattius.marvel.client.internal.ktorHttpClient
import org.koin.dsl.module

object MarvelClientKoin {

    val modules = module {
        single<MarvelApiClient> { MarvelKtorApiClient(ktorHttpClient) }
    }
}
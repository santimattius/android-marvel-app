package com.santimattius.marvel.composable.di

import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.composable.home.application.HomeViewModel
import com.santimattius.marvel.composable.home.domain.CharactersRepository
import com.santimattius.marvel.composable.home.domain.GetCharacters
import com.santimattius.marvel.composable.home.infrastructure.CharactersRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val homeAppModules = module {
    viewModel { HomeViewModel(getCharacters = get()) }
}

private val homeDomainModules = module {
    factory { GetCharacters(repository = get()) }
}

private val homeInfrastructureModules = module {
    factory<CharactersRepository> { CharactersRepositoryImpl(apiClient = get<MarvelApiClient>()) }
}

val homeModules = listOf(homeAppModules, homeDomainModules, homeInfrastructureModules)
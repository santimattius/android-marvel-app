package com.santimattius.marvel.composable.di

import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.composable.home.domain.CharactersRepository
import com.santimattius.marvel.composable.home.domain.GetCharacters
import com.santimattius.marvel.composable.home.infrastructure.CharactersRepositoryImpl
import com.santimattius.marvel.composable.home.presentation.screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    viewModel { HomeViewModel(get()) }
}

val domainModules = module {
    factory { GetCharacters(get()) }
}

val infrastructureModules = module {

    factory<CharactersRepository> { CharactersRepositoryImpl(apiClient = get<MarvelApiClient>()) }
}
package com.santimattius.marvel.composable.di

import com.santimattius.marvel.client.MarvelApiClient
import com.santimattius.marvel.composable.detail.application.DetailViewModel
import com.santimattius.marvel.composable.detail.domain.CharacterRepository
import com.santimattius.marvel.composable.detail.domain.FindCharacter
import com.santimattius.marvel.composable.detail.infrastructure.CharacterDataSource
import com.santimattius.marvel.composable.detail.infrastructure.CharacterRepositoryImpl
import com.santimattius.marvel.composable.detail.infrastructure.remote.CharacterRemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val detailAppModules = module {
    viewModel { (characterId: Long) -> DetailViewModel(characterId, get()) }
}

private val detailDomainModules = module {
    factory { FindCharacter(get()) }
}

private val detailInfrastructureModules = module {
    factory<CharacterRepository> { CharacterRepositoryImpl(get<CharacterDataSource>()) }
    factory<CharacterDataSource> { CharacterRemoteDataSource(apiClient = get<MarvelApiClient>()) }
}

val detailModules = listOf(detailAppModules, detailDomainModules, detailInfrastructureModules)

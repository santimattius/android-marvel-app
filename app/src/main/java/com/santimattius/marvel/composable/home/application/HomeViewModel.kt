package com.santimattius.marvel.composable.home.application

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.santimattius.marvel.composable.home.domain.GetCharacters

class HomeViewModel(getCharacters: GetCharacters) : ViewModel() {
    val characters = getCharacters().cachedIn(viewModelScope)
}
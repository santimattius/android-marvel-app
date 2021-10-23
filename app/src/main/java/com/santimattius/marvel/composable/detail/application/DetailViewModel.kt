package com.santimattius.marvel.composable.detail.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.marvel.composable.detail.domain.Character
import com.santimattius.marvel.composable.detail.domain.FindCharacter
import kotlinx.coroutines.launch

class DetailViewModel(id: Long, private val findCharacter: FindCharacter) :
    ViewModel() {
    private val _state = MutableLiveData<DetailState>()
    val state: LiveData<DetailState>
        get() = _state

    init {
        find(id)
    }

    private fun find(id: Long) {
        _state.value = DetailState.Loading
        viewModelScope.launch {
            _state.postValue(DetailState.Complete(findCharacter(id)))
        }
    }
}


sealed class DetailState {
    object Loading : DetailState()
    data class Complete(val data: Character) : DetailState()
}
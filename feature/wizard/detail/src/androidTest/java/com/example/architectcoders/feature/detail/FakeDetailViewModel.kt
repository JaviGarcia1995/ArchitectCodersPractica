package com.example.architectcoders.feature.detail

import com.example.architectcoders.feature.common.interfaces.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeDetailViewModel: IDetailViewModel {
    private val _state = MutableStateFlow<Result<DetailViewModel.UiState>>(Result.Success(DetailViewModel.UiState()))
    override val state: StateFlow<Result<DetailViewModel.UiState>> by lazy { _state }

    override fun toggleFavourite() {
        val currentState = _state.value
        if (currentState is Result.Success) {
            currentState.data.wizard?.let { wizard ->
                val updatedWizard = wizard.copy(isFavorite = !wizard.isFavorite)
                _state.value = Result.Success(currentState.data.copy(wizard = updatedWizard))
            }
        }
    }

    fun setState(newState: Result<DetailViewModel.UiState>) {
        _state.value = newState
    }

}
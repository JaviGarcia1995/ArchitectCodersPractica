package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import com.example.architectcoderspracticauno.ui.common.Result
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: HogwartsRepository,
    private val wizardId: String
): ViewModel() {

    val state: StateFlow<Result<UiState>>

    init {
        state = repository.findWizardById(wizardId)
            .map { UiState(wizard = it) }
            .map<UiState, Result<UiState>> { Result.Success(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Success(UiState())
            )
    }

    fun toggleFavourite() {
        val currentState = state.value
        if (currentState is Result.Success) {
            currentState.data.wizard?.let { wizard ->
                viewModelScope.launch {
                    repository.toggleFavourite(wizard)
                }
            }
        }
    }

    data class UiState(
        val wizard: WizardModel? = null,
        val error: String = ""
    )
}
package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
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

   val state: StateFlow<UiState> = repository.findWizardById(wizardId)
        .map { UiState(wizard = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState()
        )

    fun toggleFavourite() {
       state.value.wizard?.let { wizard ->
           viewModelScope.launch {
               repository.toggleFavourite(wizard)
           }
       }
    }

    data class UiState(
        val wizard: WizardModel? = null,
        val error: String = ""
    )
}
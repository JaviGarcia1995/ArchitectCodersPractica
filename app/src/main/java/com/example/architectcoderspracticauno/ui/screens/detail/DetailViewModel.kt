package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.model.toWizardModel
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: HogwartsRepository,
    private val wizardId: String
    ): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch{
            repository.findWizardById(wizardId)
                .catch { error -> _state.value = UiState(error = error.message.toString()) }
                .collect{ wizard ->
                    _state.value = UiState(wizard = wizard)
                }
        }
    }

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
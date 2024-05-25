package com.example.architectcoderspracticauno.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.model.Wizard
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val wizardId: String): ViewModel() {
    private val repository = HogwartsRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch{
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, wizard = repository.getWizardById(wizardId))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val wizard: Wizard? = null
    )
}
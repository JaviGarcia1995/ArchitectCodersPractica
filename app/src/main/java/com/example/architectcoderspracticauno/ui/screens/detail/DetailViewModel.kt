package com.example.architectcoderspracticauno.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.model.toWizardModel
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import com.example.architectcoderspracticauno.ui.model.WizardModel
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
            val wizard = repository.getWizardById(wizardId).toWizardModel()
            _state.value = UiState(wizard = wizard)
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            val isFavourite = !_state.value.isFavourite
            _state.value = _state.value.copy(isFavourite = isFavourite)
        }
    }

    data class UiState(
        val wizard: WizardModel? = null,
        val isFavourite: Boolean = false
    )
}
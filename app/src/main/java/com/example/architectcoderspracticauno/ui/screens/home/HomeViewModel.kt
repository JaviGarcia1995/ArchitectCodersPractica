package com.example.architectcoderspracticauno.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.model.toWizardModel
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val _showWelcomeToast = MutableStateFlow(false)
    val showWelcomeToast: StateFlow<Boolean> = _showWelcomeToast

    private val repository = HogwartsRepository()

    fun showWelcomeToast() {
        _showWelcomeToast.value = true
    }

    init {
        viewModelScope.launch {
            val wizards = repository.getWizardsSortedByHouse(_state.value.selectedHouse).map { it.toWizardModel() }
            _state.value = UiState(wizards = wizards)
        }
    }

    fun loadWizardsByHouse(house: String){
        viewModelScope.launch {
            val wizards = repository.getWizardsSortedByHouse(house).map { it.toWizardModel() }
            _state.value = UiState(wizards = wizards, selectedHouse = house)
        }
    }

    data class UiState(
        val wizards: List<WizardModel> = emptyList(),
        val selectedHouse: String = "gryffindor"
    )
}
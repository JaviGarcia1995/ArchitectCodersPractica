package com.example.architectcoderspracticauno.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import com.example.architectcoderspracticauno.ui.model.WizardModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

private const val DEFAULT_HOUSE = "gryffindor"

class HomeViewModel(
    private val repository: HogwartsRepository
): ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val _showedWelcomeToast = MutableStateFlow(false)
    val showedWelcomeToast: StateFlow<Boolean> = _showedWelcomeToast

    private var fetchJob: Job? = null

    fun setShowedWelcomeToast() {
        _showedWelcomeToast.value = true
    }

    init {
        loadWizardsByHouse(DEFAULT_HOUSE)
    }

    fun loadWizardsByHouse(house: String){
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            repository.fetchWizardsByHouse(house)
                .catch { error -> _state.value = _state.value.copy(error = error.message.toString()) }
                .collect{ wizards ->
                    _state.value = UiState(wizards = wizards, selectedHouse = house)
                }
        }
    }

    data class UiState(
        val wizards: List<WizardModel> = emptyList(),
        val selectedHouse: String = DEFAULT_HOUSE,
        val error : String = ""
    )
}
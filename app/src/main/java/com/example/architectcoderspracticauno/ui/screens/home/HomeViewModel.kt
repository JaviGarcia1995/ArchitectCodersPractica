package com.example.architectcoderspracticauno.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoderspracticauno.data.model.Wizard
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    private val repository = HogwartsRepository()

    fun loadWizardsByHouse(house: String){
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, repository.getWizardsSortedByHouse(house))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val wizards: List<Wizard> = emptyList()
    )
}
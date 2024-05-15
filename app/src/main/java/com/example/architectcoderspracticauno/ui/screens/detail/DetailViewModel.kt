package com.example.architectcoderspracticauno.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.architectcoderspracticauno.data.model.Wizard
import com.example.architectcoderspracticauno.data.repository.HogwartsRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val wizardId: String): ViewModel() {
    private val repository = HogwartsRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch{
            state = UiState(loading = true)
            state = UiState(loading = false, wizard = repository.getWizardById(wizardId))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val wizard: Wizard? = null
    )
}
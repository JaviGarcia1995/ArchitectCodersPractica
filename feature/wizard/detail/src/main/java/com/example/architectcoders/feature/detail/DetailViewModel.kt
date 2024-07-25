package com.example.architectcoders.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.usecases.FindWizardByIdUseCase
import com.example.architectcoders.domain.wizard.usecases.ToggleFavoriteUseCase
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.feature.detail.hilt.WizardId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @WizardId wizardId: String,
    private val findWizardByIdUseCase: FindWizardByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
): ViewModel(), IDetailViewModel {

    override val state: StateFlow<Result<UiState>>

    init {
        state = findWizardByIdUseCase(wizardId)
            .map { UiState(wizard = it) }
            .map<UiState, Result<UiState>> { Result.Success(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = Result.Success(UiState())
            )
    }

    override fun toggleFavourite() {
        val currentState = state.value
        if (currentState is Result.Success) {
            currentState.data.wizard?.let { wizard ->
                viewModelScope.launch {
                    toggleFavoriteUseCase(wizard)
                }
            }
        }
    }

    data class UiState(
        val wizard: WizardModel? = null,
        val error: String = ""
    )
}
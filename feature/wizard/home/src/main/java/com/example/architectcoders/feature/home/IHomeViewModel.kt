package com.example.architectcoders.feature.home

import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.feature.home.HomeViewModel.UiState
import kotlinx.coroutines.flow.StateFlow
import com.example.architectcoders.feature.common.interfaces.Result

interface IHomeViewModel {
    val state: StateFlow<Result<UiState>>
    val favoriteWizards: StateFlow<Result<List<WizardModel>>>
    val showedWelcomeToast: StateFlow<Boolean>

    fun loadWizardsByHouse(house: String)
    fun setShowedWelcomeToast()
}
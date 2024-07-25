package com.example.architectcoders.feature.home

import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.sampleWizards
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.architectcoders.feature.common.interfaces.Result

class FakeHomeViewModel(
    private val sampleFavoriteWizards: List<WizardModel> = emptyList()
): IHomeViewModel {
    private val _state = MutableStateFlow<Result<HomeViewModel.UiState>>(Result.Success(HomeViewModel.UiState()))
    override val state: StateFlow<Result<HomeViewModel.UiState>> by lazy { _state }

    private val _favoriteWizards = MutableStateFlow<Result<List<WizardModel>>>(Result.Success(sampleFavoriteWizards))
    override val favoriteWizards: StateFlow<Result<List<WizardModel>>> by lazy { _favoriteWizards }

    private val _showedWelcomeToast = MutableStateFlow(false)
    override val showedWelcomeToast: StateFlow<Boolean> by lazy { _showedWelcomeToast }

    override fun loadWizardsByHouse(house: String) {
        _state.value = Result.Success(HomeViewModel.UiState(
            wizards = sampleWizards("1", "2", "3"), selectedHouse = "Gryffindor")
        )
    }

    override fun setShowedWelcomeToast() {
        _showedWelcomeToast.value = true
    }
}
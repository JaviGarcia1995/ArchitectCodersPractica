package com.example.architectcoders.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.usecases.FetchFavoriteWizardsUseCase
import com.example.architectcoders.domain.wizard.usecases.FetchWizardsByHouseUseCase
import com.example.architectcoders.feature.common.capitalize
import com.example.architectcoders.feature.common.interfaces.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

private const val DEFAULT_HOUSE = "gryffindor"

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val fetchWizardsByHouseUseCase: FetchWizardsByHouseUseCase,
    private val fetchFavoriteWizardsUseCase: FetchFavoriteWizardsUseCase
): ViewModel(), IHomeViewModel {

    private val _showedWelcomeToast = MutableStateFlow(false)
    override val showedWelcomeToast: StateFlow<Boolean> = _showedWelcomeToast

    private val _selectedHouse = MutableStateFlow(DEFAULT_HOUSE)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<Result<UiState>> = _selectedHouse
        .flatMapLatest { house ->
            fetchWizardsByHouseUseCase(house.capitalize())
                .map { wizards ->
                    UiState(wizards = wizards, selectedHouse = house)
                }
                .map<UiState, Result<UiState>> { Result.Success(it) }
                .catch { e -> emit(Result.Error(e)) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Result.Success(UiState())
        )

    override val favoriteWizards: StateFlow<Result<List<WizardModel>>> = fetchFavoriteWizardsUseCase()
        .map<List<WizardModel>, Result<List<WizardModel>>> { Result.Success(it) }
        .catch { e -> emit(Result.Error(e)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Result.Success(emptyList())
        )

    override fun loadWizardsByHouse(house: String){
        _selectedHouse.value = house
    }

    override fun setShowedWelcomeToast() {
        _showedWelcomeToast.value = true
    }

    data class UiState(
        val wizards: List<WizardModel> = emptyList(),
        val selectedHouse: String = DEFAULT_HOUSE,
        val error : String = ""
    )
}
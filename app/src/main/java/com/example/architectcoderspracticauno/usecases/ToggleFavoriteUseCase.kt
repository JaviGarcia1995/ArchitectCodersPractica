package com.example.architectcoderspracticauno.usecases

import com.example.architectcoderspracticauno.framework.remote.HogwartsRepository
import com.example.architectcoderspracticauno.ui.model.WizardModel

class ToggleFavoriteUseCase(private val hogwartsRepository: HogwartsRepository) {
    suspend operator fun invoke(wizard: WizardModel) = hogwartsRepository.toggleFavourite(wizard)
}
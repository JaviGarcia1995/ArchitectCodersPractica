package com.example.architectcoderspracticauno.usecases

import com.example.architectcoderspracticauno.framework.remote.HogwartsRepository

class FindWizardByIdUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(wizardId: String) = hogwartsRepository.findWizardById(wizardId)
}
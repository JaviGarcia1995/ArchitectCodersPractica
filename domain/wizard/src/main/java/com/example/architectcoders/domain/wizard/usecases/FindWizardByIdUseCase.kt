package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository

class FindWizardByIdUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(wizardId: String) = hogwartsRepository.findWizardById(wizardId)
}
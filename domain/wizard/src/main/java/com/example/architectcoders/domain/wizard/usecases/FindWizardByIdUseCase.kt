package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import javax.inject.Inject

class FindWizardByIdUseCase @Inject constructor(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(wizardId: String) = hogwartsRepository.findWizardById(wizardId)
}
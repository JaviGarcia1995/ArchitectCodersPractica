package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository
import javax.inject.Inject

class FetchWizardsByHouseUseCase @Inject constructor(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(house: String) = hogwartsRepository.fetchWizardsByHouse(house)
}
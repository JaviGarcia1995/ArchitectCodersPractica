package com.example.architectcoders.domain.wizard.usecases

import com.example.architectcoders.domain.wizard.data.HogwartsRepository

class FetchWizardsByHouseUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(house: String) = hogwartsRepository.fetchWizardsByHouse(house)
}
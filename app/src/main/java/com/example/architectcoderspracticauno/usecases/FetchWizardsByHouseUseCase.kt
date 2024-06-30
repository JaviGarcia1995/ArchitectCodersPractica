package com.example.architectcoderspracticauno.usecases

import com.example.architectcoderspracticauno.framework.remote.HogwartsRepository

class FetchWizardsByHouseUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke(house: String) = hogwartsRepository.fetchWizardsByHouse(house)
}
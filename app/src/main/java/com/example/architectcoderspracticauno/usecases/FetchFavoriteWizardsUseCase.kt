package com.example.architectcoderspracticauno.usecases

import com.example.architectcoderspracticauno.framework.remote.HogwartsRepository

class FetchFavoriteWizardsUseCase(private val hogwartsRepository: HogwartsRepository) {
    operator fun invoke() = hogwartsRepository.fetchFavoriteWizards()
}
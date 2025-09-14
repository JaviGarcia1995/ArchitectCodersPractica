package com.example.architectcoders.feature.detail

import app.cash.turbine.test
import com.example.architectcoders.data.buildHogwartsRepositoryWith
import com.example.architectcoders.domain.wizard.sampleWizard
import com.example.architectcoders.domain.wizard.sampleWizards
import com.example.architectcoders.domain.wizard.usecases.FindWizardByIdUseCase
import com.example.architectcoders.domain.wizard.usecases.ToggleFavoriteUseCase
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailIntegrationTest {
    @get:Rule val coroutinesTestRule = CoroutinesTestRule()
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        val hogwartsRepository = buildHogwartsRepositoryWith(localData = sampleWizards("1", "2", "3"))
        viewModel = DetailViewModel(
            wizardId = "1",
            findWizardByIdUseCase = FindWizardByIdUseCase(hogwartsRepository),
            toggleFavoriteUseCase = ToggleFavoriteUseCase(hogwartsRepository)
        )
    }

    @Test
    fun `UI is updated with the wizard on start`() = runTest {
        viewModel.state.test {
            assertEquals(Result.Success(sampleWizard("1")), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite is updated in local data source`() = runTest {
        viewModel.state.test{
            assertEquals(Result.Success(sampleWizard("")), awaitItem())

            viewModel.toggleFavourite()
            runCurrent()

            assertEquals(Result.Success(sampleWizard("2").copy(isFavorite = true)), awaitItem())
        }
    }
}
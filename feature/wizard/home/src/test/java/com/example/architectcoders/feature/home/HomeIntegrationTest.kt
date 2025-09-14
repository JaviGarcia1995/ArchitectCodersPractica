package com.example.architectcoders.feature.home

import app.cash.turbine.test
import com.example.architectcoders.data.buildHogwartsRepositoryWith
import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.domain.wizard.sampleWizards
import com.example.architectcoders.domain.wizard.usecases.FetchFavoriteWizardsUseCase
import com.example.architectcoders.domain.wizard.usecases.FetchWizardsByHouseUseCase
import com.example.architectcoders.testrules.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import com.example.architectcoders.feature.common.interfaces.Result
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTest {
    @get:Rule val coroutinesTestRule = CoroutinesTestRule()
    private lateinit var viewModel: HomeViewModel


    private fun buildViewModelWith(localData: List<WizardModel> = emptyList(), remoteData: List<WizardModel> = emptyList()) =
        HomeViewModel(
            fetchWizardsByHouseUseCase = FetchWizardsByHouseUseCase(
                buildHogwartsRepositoryWith(localData, remoteData)
            ),
            fetchFavoriteWizardsUseCase = FetchFavoriteWizardsUseCase(
                buildHogwartsRepositoryWith(localData, remoteData)
            )
        )

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest{
        val remoteData = sampleWizards("1", "2", "3")
        val viewModel = buildViewModelWith(remoteData = remoteData)

        viewModel.state.test{
            assertEquals(Result.Success(emptyList<WizardModel>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }
}
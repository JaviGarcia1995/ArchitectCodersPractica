package com.example.architectcoders.feature.home

import app.cash.turbine.test
import com.example.architectcoders.domain.wizard.sampleWizards
import com.example.architectcoders.domain.wizard.usecases.FetchFavoriteWizardsUseCase
import com.example.architectcoders.domain.wizard.usecases.FetchWizardsByHouseUseCase
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest{
    @get:Rule val coroutinesTestRule = CoroutinesTestRule()
    @Mock lateinit var fetchWizardsByHouseUseCase: FetchWizardsByHouseUseCase
    @Mock lateinit var fetchFavoriteWizardsUseCase: FetchFavoriteWizardsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(fetchWizardsByHouseUseCase, fetchFavoriteWizardsUseCase)
    }

    @Test
    fun `Wizards requested when init Home view model`() = runTest {
        val wizards = sampleWizards("1", "2", "3")
        whenever(fetchWizardsByHouseUseCase("Gryffindor")).thenReturn(flowOf(wizards))

        viewModel.state.test {
            assertEquals(
                Result.Success(wizards),
                awaitItem()
            )
        }
    }

    @Test
    fun `Error is propagated when request fails`(): Unit = runTest {
        val error = RuntimeException("Boom!")
        whenever(fetchWizardsByHouseUseCase("Gryffindor")).thenThrow(error)

        viewModel.state.test {
            val exception = (awaitItem() as Result.Error).exception.message
            assertEquals(error.message, exception)
        }
    }

    @Test
    fun `Load favorites wizards`() = runTest {
        val favoriteWizards = sampleWizards("1", "2", "3")
        whenever(fetchFavoriteWizardsUseCase()).thenReturn(flowOf(favoriteWizards))

        viewModel.favoriteWizards.test {
            assertEquals(
                Result.Success(favoriteWizards),
                awaitItem()
            )
        }
    }
}
package com.example.architectcoders.feature.detail

import app.cash.turbine.test
import com.example.architectcoders.domain.wizard.sampleWizard
import com.example.architectcoders.domain.wizard.usecases.FindWizardByIdUseCase
import com.example.architectcoders.domain.wizard.usecases.ToggleFavoriteUseCase
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.testrules.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{
    @get:Rule val coroutinesTestRule = CoroutinesTestRule()
    @Mock lateinit var findWizardByIdUseCase: FindWizardByIdUseCase
    @Mock lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase
    private lateinit var viewModel: DetailViewModel
    private val wizard = sampleWizard("1")

    @Before
    fun setUp() {
        whenever(findWizardByIdUseCase("1")).thenReturn(flowOf(wizard))
        viewModel = DetailViewModel(
            wizardId = "1",
            findWizardByIdUseCase = findWizardByIdUseCase,
            toggleFavoriteUseCase = toggleFavoriteUseCase
        )
    }

    @Test
    fun `UI is updated with the movie on start`(): Unit = runTest {
        viewModel.state.test {
            assertEquals(Result.Success(wizard), awaitItem())
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Favorite action calls the corresponding case`(): Unit = runTest(coroutinesTestRule.testDispatcher) {
        viewModel.state.test{
            assertEquals(Result.Success(wizard), awaitItem())

            viewModel.toggleFavourite()
            runCurrent()

            verify(toggleFavoriteUseCase).invoke(wizard)
        }
    }
}
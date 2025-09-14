package com.example.architectcoders.feature.detail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.domain.wizard.sampleWizard
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeViewModel: FakeDetailViewModel

    @Before
    fun setup() {
        fakeViewModel = FakeDetailViewModel()
    }

    @Test
    fun toggleFavourite_updatesFavouriteState(): Unit = with(composeTestRule) {
        setContent {
            DetailScreen(
                vm = FakeDetailViewModel(),
                onBack = {}
            )
        }
        val wizard = sampleWizard("1")
        val initialState = Result.Success(DetailViewModel.UiState(wizard = wizard))
        fakeViewModel.setState(initialState)

        fakeViewModel.toggleFavourite()

        runTest {
            val newState = fakeViewModel.state.first()
            if (newState is Result.Success) {
                assertEquals(true, newState.data.wizard?.isFavorite)
            }
        }
    }

    @Test
    fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
        var clicked = false
        setContent {
            DetailScreen(
                vm = FakeDetailViewModel(),
                onBack = { clicked = true }
            )
        }

        onNodeWithContentDescription("Go Back").performClick()
        assertTrue(clicked)
    }
}

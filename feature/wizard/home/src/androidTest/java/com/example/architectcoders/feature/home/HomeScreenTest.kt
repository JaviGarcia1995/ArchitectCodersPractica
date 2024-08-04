package com.example.architectcoders.feature.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreenDisplaysWelcomeToast(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                viewModel = FakeHomeViewModel(),
                onWizardClicked = {}
            )
        }
        onNodeWithText("¡Bienvenido/a!").assertIsDisplayed()
    }

    @Test
    fun whenSuccessResult_showWizards(): Unit = with(composeTestRule) {
        setContent {
            HomeScreen(
                viewModel = FakeHomeViewModel(),
                onWizardClicked = {}
            )
        }
        onNodeWithText("Gryffindor").assertIsDisplayed()
    }

    @Test
    fun whenMovieClicked_listenerIsCalled(): Unit = with(composeTestRule){
        var clickWizardId = "-1"
        setContent {
            HomeScreen(
                viewModel = FakeHomeViewModel(),
                onWizardClicked = { clickWizardId = it.id }
            )
        }
        onNodeWithText("Wizard 2").performClick()
        assertEquals("2", clickWizardId)
    }
}
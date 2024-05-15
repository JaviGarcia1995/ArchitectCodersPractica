package com.example.architectcoderspracticauno.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.architectcoderspracticauno.ui.screens.detail.DetailScreen
import com.example.architectcoderspracticauno.ui.screens.detail.DetailViewModel
import com.example.architectcoderspracticauno.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>{
            HomeScreen( onWizardClicked = { wizard ->
                navController.navigate(Detail(wizard.id))
            })
        }
        composable<Detail>{backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailScreen(
                viewModel { DetailViewModel(detail.wizardId) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
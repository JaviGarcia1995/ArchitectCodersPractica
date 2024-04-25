package com.example.architectcoderspracticauno.ui.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.architectcoderspracticauno.ui.screens.detail.DetailScreen
import com.example.architectcoderspracticauno.ui.screens.detail.DetailViewModel
import com.example.architectcoderspracticauno.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            HomeScreen( onWizardClicked = {
                navController.navigate("detail/${it.id}")
            })
        }
        composable(
            route = "detail/{wizardId}",
            arguments = listOf(navArgument("wizardId") { type = NavType.StringType})
        ){backStackEntry ->
            val wizardId = requireNotNull(backStackEntry.arguments?.getString("wizardId"))
            Log.d("[DEBUG]", wizardId)
            DetailScreen(
                // ¿Se crea un viewModel cada vez que se pulsa un detalle?
                // ¿Y pasar solo la id?
                viewModel { DetailViewModel(wizardId) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}

// Parpadeos cada vez que navega, da igual de que pantalla a qué pantalla
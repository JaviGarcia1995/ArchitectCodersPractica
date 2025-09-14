package com.example.architectcoderspracticauno.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.architectcoders.feature.detail.DetailScreen
import com.example.architectcoders.feature.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val animationDuration = 800

    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(animationDuration)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(animationDuration)) },
        ){
            HomeScreen(
                onWizardClicked = { wizard ->
                    navController.navigate(Detail(wizard.id))
                })
        }
        composable<Detail>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(animationDuration)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(animationDuration)) },
        ){
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
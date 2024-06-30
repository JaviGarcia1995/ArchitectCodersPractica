package com.example.architectcoderspracticauno.ui.screens.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.architectcoderspracticauno.App
import com.example.architectcoderspracticauno.framework.datasource.RoomWizardsDataSource
import com.example.architectcoderspracticauno.framework.datasource.ServerWizardsDataSource
import com.example.architectcoderspracticauno.framework.remote.HogwartsClient
import com.example.architectcoderspracticauno.framework.remote.HogwartsRepository
import com.example.architectcoderspracticauno.ui.screens.detail.DetailScreen
import com.example.architectcoderspracticauno.ui.screens.detail.DetailViewModel
import com.example.architectcoderspracticauno.ui.screens.home.HomeScreen
import com.example.architectcoderspracticauno.ui.screens.home.HomeViewModel
import com.example.architectcoderspracticauno.usecases.FetchFavoriteWizardsUseCase
import com.example.architectcoderspracticauno.usecases.FetchWizardsByHouseUseCase
import com.example.architectcoderspracticauno.usecases.FindWizardByIdUseCase
import com.example.architectcoderspracticauno.usecases.ToggleFavoriteUseCase

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val animationDuration = 800
    val app = LocalContext.current.applicationContext as App
    val repository = remember {
        HogwartsRepository(
            remoteWizardsDataSource = ServerWizardsDataSource(HogwartsClient.instance),
            localWizardsDataSource = RoomWizardsDataSource(app.db.wizardDao())
        )
    }

    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(animationDuration)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(animationDuration)) },
        ){
            HomeScreen(
                vm = viewModel { HomeViewModel(
                    FetchWizardsByHouseUseCase(repository),
                    FetchFavoriteWizardsUseCase(repository)
                )},
                onWizardClicked = { wizard ->
                    navController.navigate(Detail(wizard.id))
                })
        }
        composable<Detail>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(animationDuration)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(animationDuration)) },
        ){backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            DetailScreen(
                vm = viewModel {
                    DetailViewModel(
                        wizardId = detail.wizardId,
                        FindWizardByIdUseCase(repository),
                        ToggleFavoriteUseCase(repository),
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
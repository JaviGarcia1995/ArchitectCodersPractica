package com.example.architectcoderspracticauno.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.architectcoderspracticauno.ui.common.BottomNavBar
import com.example.architectcoderspracticauno.ui.common.ChangeStatusBarColor
import com.example.architectcoderspracticauno.ui.common.LoadImage
import com.example.architectcoderspracticauno.ui.common.Screen
import com.example.architectcoderspracticauno.ui.common.getColorByHouse
import com.example.architectcoderspracticauno.ui.model.WizardModel
import com.example.architectcoderspracticauno.ui.theme.BackgroundApp
import com.example.architectcoderspracticauno.ui.theme.SelectedBarItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onWizardClicked: (WizardModel) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    val showWelcomeToast by vm.showWelcomeToast.collectAsState()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val state by vm.state.collectAsState()

    Screen {
        ChangeStatusBarColor()

        LaunchedEffect(showWelcomeToast) {
            if (!showWelcomeToast) {
                Toast.makeText(context, "¡Bienvenido/a!" , Toast.LENGTH_SHORT).show()
                vm.showWelcomeToast()
            }
        }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = ({ BottomNavBar(vm) }),
            floatingActionButton = {
                HomeFloatingButton(
                    onClick = {  },
                    house = state.selectedHouse
                )
            }
        ) { padding ->
            LazyVerticalGrid(
                modifier = Modifier
                    .background(BackgroundApp)
                    .padding(8.dp)
                    .fillMaxSize(),
                columns = GridCells.Adaptive(120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = padding,
            ) {
                items(state.wizards, key = { it.id }){ wizard ->
                    WizardItem(
                        wizard = wizard,
                        onWizardClicked = { onWizardClicked(wizard) }
                    )
                }
            }
        }
    }
}

@Composable
private fun WizardItem(wizard: WizardModel, onWizardClicked: () -> Unit) {
    Column(
        modifier = Modifier.clickable { onWizardClicked() }
    ) {
        LoadImage(wizard)

        Text(
            text = wizard.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun HomeFloatingButton(
    onClick: () -> Unit,
    house: String,
) {
    FloatingActionButton(
        onClick =  onClick,
        shape = CircleShape,
        modifier = Modifier.padding(16.dp),
        containerColor = SelectedBarItem,
        contentColor = getColorByHouse(house)
    ){
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Show my favourites wizards"
        )
    }
}
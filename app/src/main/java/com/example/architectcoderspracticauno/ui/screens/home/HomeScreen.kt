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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.architectcoderspracticauno.ui.common.BottomNavBar
import com.example.architectcoderspracticauno.ui.common.ChangeStatusBarColor
import com.example.architectcoderspracticauno.ui.common.LoadImage
import com.example.architectcoderspracticauno.ui.common.Screen
import com.example.architectcoderspracticauno.ui.model.WizardModel
import com.example.architectcoderspracticauno.ui.theme.BackgroundApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vm: HomeViewModel,
    onWizardClicked: (WizardModel) -> Unit
) {
    val context = LocalContext.current
    val state by vm.state.collectAsState()
    val showedWelcomeToast by vm.showedWelcomeToast.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        ChangeStatusBarColor()

        LaunchedEffect(showedWelcomeToast) {
            if (!showedWelcomeToast) {
                Toast.makeText(context, "¡Bienvenido/a!" , Toast.LENGTH_SHORT).show()
                vm.setShowedWelcomeToast()
            }
        }

        LaunchedEffect(state.error) {
            if (state.error.isNotEmpty()){
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }
        }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = ({ BottomNavBar(vm) }),
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
package com.example.architectcoders.feature.home

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.architectcoders.domain.wizard.entities.WizardModel
import com.example.architectcoders.feature.common.ChangeStatusBarColor
import com.example.architectcoders.feature.common.LoadImage
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.feature.common.Screen
import com.example.architectcoders.feature.common.theme.BackgroundApp
import com.example.architectcoders.feature.common.theme.BackgroundBars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onWizardClicked: (WizardModel) -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val favoriteWizards by viewModel.favoriteWizards.collectAsState()
    val showedWelcomeToast by viewModel.showedWelcomeToast.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val showSheetState = remember { mutableStateOf(false) }

    Screen {
        ChangeStatusBarColor()

        LaunchedEffect(showedWelcomeToast) {
            if (!showedWelcomeToast) {
                Toast.makeText(context, "¡Bienvenido/a!", Toast.LENGTH_SHORT).show()
                viewModel.setShowedWelcomeToast()
            }
        }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = ({ BottomNavBar(viewModel, showSheetState) }),
        ) { padding ->

            when (val currentState = state) {
                is Result.Error -> {
                    Toast.makeText(
                        context,
                        "Error. Wizards could not be loaded. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Success -> {
                    WizardList(
                        showSheetState = showSheetState,
                        padding = padding,
                        wizards = currentState.data.wizards,
                        onWizardClicked = onWizardClicked
                    )
                }
            }

            when (val wizards = favoriteWizards) {
                is Result.Error -> {
                    Toast.makeText(
                        context,
                        "Error. Favorite Wizards could not be loaded. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Success -> {
                    FavoritesBottomSheet(
                        sheetState = sheetState,
                        bottomSheetState = showSheetState,
                        favoriteWizards = wizards.data
                    )
                }
            }
        }
    }
}

@Composable
private fun WizardList(
    showSheetState: MutableState<Boolean>,
    padding: PaddingValues,
    wizards: List<WizardModel>,
    onWizardClicked: (WizardModel) -> Unit
) {
    val blurRadius by animateDpAsState(
        targetValue = if (showSheetState.value) 6.dp else 0.dp,
        animationSpec = tween(durationMillis = 100),
        label = "Blur radius"
    )

    LazyVerticalGrid(
        modifier = Modifier
            .background(BackgroundApp)
            .padding(8.dp)
            .fillMaxSize()
            .blur(blurRadius),
        columns = GridCells.Adaptive(120.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = padding,
    ) {
        items(wizards, key = { it.id }){ wizard ->
            WizardItem(
                wizard = wizard,
                onWizardClicked = { onWizardClicked(wizard) }
            )
        }
    }
}

@Composable
private fun WizardItem(
    wizard: WizardModel,
    onWizardClicked: () -> Unit
) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesBottomSheet(
    sheetState: SheetState,
    bottomSheetState: MutableState<Boolean>,
    favoriteWizards: List<WizardModel>
) {
    if (bottomSheetState.value) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetState.value = false },
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            containerColor = Color(BackgroundBars.value),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(4.dp))
                        .width(40.dp)
                        .height(4.dp)
                )
            }
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Sheet content
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Favorite Wizards",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    columns = GridCells.Adaptive(120.dp),
                ) {
                    items(favoriteWizards){ wizard ->
                        WizardItem(
                            wizard = wizard,
                            onWizardClicked = { }
                        )
                    }
                }
            }
        }
    }
}
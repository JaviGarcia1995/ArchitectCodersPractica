package com.example.architectcoderspracticauno.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.ui.screens.home.HomeViewModel
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars
import com.example.architectcoderspracticauno.ui.theme.SelectedBarItem

data class BottomNavItem(
    val label: String,
    val drawableIcon: Int
) {
    companion object {
        val Gryffindor = BottomNavItem("Gryffindor", R.drawable.ic_gryffindor)
        val Slytherin = BottomNavItem("Slytherin", R.drawable.ic_slytherin)
        val Ravenclaw = BottomNavItem("Ravenclaw", R.drawable.ic_ravenclaw)
        val Hufflepuff = BottomNavItem("Hufflepuff", R.drawable.ic_hufflepuff)
    }
}

@Composable
fun BottomNavBar(vm: HomeViewModel) {
    val items = listOf(
        BottomNavItem.Gryffindor,
        BottomNavItem.Slytherin,
        BottomNavItem.Ravenclaw,
        BottomNavItem.Hufflepuff
    )

    val selectedNavItem = remember { mutableStateOf(BottomNavItem.Gryffindor) }

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = BackgroundBars,

        ) {
        items.forEach { item ->
            AddItem(
                navItem = item,
                vm = vm,
                selectedNavItem = selectedNavItem.value,
                onItemSelected = { selectedNavItem.value = item }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    navItem: BottomNavItem,
    vm: HomeViewModel,
    selectedNavItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    val isSelected = navItem == selectedNavItem
    NavigationBarItem(
        modifier = Modifier
            .background(BackgroundBars) ,
        // Text that shows bellow the icon
        label = {
            AnimatedVisibility(
                modifier = Modifier
                    .background(BackgroundBars),
                visible = isSelected,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(text = navItem.label, color = Color.White)
            }
        },

        // The icon resource
        icon = {
            Icon(
                painterResource(id = navItem.drawableIcon),
                contentDescription = navItem.label,
                tint = Color.Unspecified
            )
        },

        // Display if the icon it is select or not
        selected = isSelected,

        // Always show the label bellow the icon or not
        alwaysShowLabel = false,

        // Click listener for the icon
        onClick = {
            onItemSelected(navItem)
            vm.loadWizardsByHouse(navItem.label)
        },

        colors = NavigationBarItemDefaults.colors(
            indicatorColor = SelectedBarItem
        )
    )
}
package com.example.architectcoderspracticauno.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.architectcoderspracticauno.R
import com.example.architectcoderspracticauno.ui.screens.home.HomeViewModel
import com.example.architectcoderspracticauno.ui.theme.BackgroundBars
import com.example.architectcoderspracticauno.ui.theme.SelectedBarItem

data class BottomNavItem(
    val label: String,
    val drawableIcon: Int
)

private val navItems = listOf(
    BottomNavItem("Gryffindor", R.drawable.ic_gryffindor),
    BottomNavItem("Slytherin", R.drawable.ic_slytherin),
    BottomNavItem("Ravenclaw", R.drawable.ic_ravenclaw),
    BottomNavItem("Hufflepuff", R.drawable.ic_hufflepuff)
)

@Composable
fun BottomNavBar(vm: HomeViewModel) {
    val selectedHouse = remember { mutableStateOf(navItems.first()) }

    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = BackgroundBars,
    ) {
        navItems.forEach { item ->
            val isSelected = selectedHouse.value == item

            BottomNavigationItem(
                icon = {
                    val icon: Painter = painterResource(id = item.drawableIcon)
                    Icon(
                        painter = icon,
                        contentDescription = item.label,
                        tint = Color.Unspecified
                    )
                },
                label = {
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ){
                        Text(text = item.label, color = Color.White)
                    }

                },
                selected = isSelected,
                alwaysShowLabel = false,
                onClick = {
                    selectedHouse.value = item
                    vm.loadWizardsByHouse(item.label)
                },
                selectedContentColor = SelectedBarItem,
                unselectedContentColor = Color.White
            )
        }
    }
}
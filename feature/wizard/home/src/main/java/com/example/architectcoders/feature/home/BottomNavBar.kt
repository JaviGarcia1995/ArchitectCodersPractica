package com.example.architectcoders.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.architectcoders.feature.common.getColorByHouse
import com.example.architectcoders.feature.common.theme.BackgroundBars
import com.example.architectcoders.feature.common.interfaces.Result
import com.example.architectcoders.feature.common.theme.SelectedBarItem
import com.example.home.R

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
fun BottomNavBar(viewModel: HomeViewModel, showSheetState: MutableState<Boolean>) {
    val state by viewModel.state.collectAsState()
    val selectedHouse = when (val result = state) {
        is Result.Success -> {
            navItems.find { it.label == result.data.selectedHouse } ?: navItems.first()
        }
        else -> navItems.first()
    }
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        val itemCount = navItems.size
        val halfItemCount = itemCount / 2

        BottomNavigation(
            backgroundColor = BackgroundBars,
        ) {
            navItems.forEachIndexed() { index, item ->
                val isSelected = selectedHouse == item

                BottomNavigationItem(
                    modifier = Modifier.padding(vertical = 8.dp),
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
                        ) {
                            Text(
                                text = item.label,
                                color = Color.White,
                                fontSize = 12.sp,
                            )
                        }
                    },
                    selected = isSelected,
                    alwaysShowLabel = false,
                    onClick = {
                        if (!isSelected) {
                            viewModel.loadWizardsByHouse(item.label)
                        }
                    },
                )

                if (index == halfItemCount - 1){
                    HomeFloatingButton(
                        onClick = {
                            showSheetState.value = true

                        },
                        house = selectedHouse.label
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeFloatingButton(
    onClick: () -> Unit,
    house: String,
) {
    val color by animateColorAsState(
        targetValue = getColorByHouse(house),
        animationSpec = tween(700),
        label = "FloatingButtonIconAnimation"
    )

    FloatingActionButton(
        modifier = Modifier.padding(8.dp),
        onClick =  onClick,
        shape = CircleShape,
        containerColor = SelectedBarItem,
        contentColor = color
    ){
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Show my favourites wizards"
        )
    }
}
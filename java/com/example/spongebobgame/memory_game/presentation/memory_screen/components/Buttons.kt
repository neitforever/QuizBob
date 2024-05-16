package com.example.spongebobgame.memory_game.presentation.memory_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.spongebobgame.memory_game.presentation.memory_screen.MemoryEvent
import com.example.spongebobgame.memory_game.presentation.memory_screen.MemoryViewModel

@Composable
fun BuildButtons(
    viewModel: MemoryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val state = viewModel.state.value

    IconButton(
        onClick = { viewModel.onEvent(MemoryEvent.SetPairsToSix) },
        icon = Icons.Default.KeyboardArrowDown,
        contentDescription = "Set Pairs to Six",
        tint = state.currentTheme.iconColor,
        modifier = modifier
    )
    IconButton(
        onClick = { viewModel.onEvent(MemoryEvent.SetPairsToNine) },
        icon = Icons.Default.KeyboardArrowUp,
        contentDescription = "Set Pairs to Nine",
        tint = state.currentTheme.iconColor,
        modifier = modifier
    )
    IconButton(
        onClick = { viewModel.onEvent(MemoryEvent.ResetGame) },
        icon = Icons.Default.Refresh,
        contentDescription = "Reset Game Button",
        tint = state.currentTheme.iconColor,
        modifier = modifier
    )
    IconButton(
        onClick = {
            viewModel.onEvent(MemoryEvent.CloseGame)
            navController.navigate("main_screen")
        },
        icon = Icons.Default.Close,
        contentDescription = "Close Game Button",
        tint = state.currentTheme.iconColor,
        modifier = modifier
    )
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Button(
            onClick = onClick,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}
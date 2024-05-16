package com.example.spongebobgame.memory_game.presentation.memory_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.spongebobgame.memory_game.domain.model.MemoryCard
import com.example.spongebobgame.memory_game.presentation.memory_screen.MemoryState
import com.example.spongebobgame.memory_game.presentation.util.NumericValues.card_image_aspect_ratio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryGameCard(
    card: MemoryCard,
    modifier: Modifier = Modifier,
    state: MemoryState,
    onClick: () -> Unit
){
    if(card.isBackDisplayed){
        val localDensity = LocalDensity.current
        var cardHeight by remember { mutableStateOf(0.dp) }
        var cardWidth by remember { mutableStateOf(0.dp) }
        Card(
            shape = RoundedCornerShape(12.dp),
            onClick = onClick,
            colors = CardDefaults.cardColors( containerColor =  state.currentTheme.cardBaseColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = modifier
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        cardHeight = with(localDensity) { coordinates.size.height.toDp() }
                        cardWidth = with(localDensity) { coordinates.size.width.toDp() }
                    },
                contentAlignment = Alignment.Center
            ){
                val cardAspectRatio = cardWidth / cardHeight
                val shouldUseFillWidth = cardAspectRatio > card_image_aspect_ratio
                Image(
                    painter = painterResource(id = state.currentTheme.cardback),
                    contentDescription = "Back of card image",
                    contentScale =
                    if(shouldUseFillWidth) ContentScale.FillWidth else ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = state.currentTheme.cardbackBorder),
                    alignment = Alignment.Center
                )
            }
        }
    } else {
        val borderModifier = if(card.matchFound){
            modifier.border(
                width = 4.dp,
                shape = RoundedCornerShape(12.dp),
                color = state.currentTheme.matchedOutlineColor
            )
        }else{
            modifier
        }
        OutlinedCard(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = state.currentTheme.cardFrontBaseColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = borderModifier.border(
                width = 2.dp,
                shape = RoundedCornerShape(12.dp),
                color = state.currentTheme.cardFrontBorderColor)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = state.currentTheme.getImageResourceForNumber(card.value)!!),
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = state.currentTheme.matchedOutlineColor),
                    contentDescription = "memory card ${card.value}",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
            }
        }
    }
}
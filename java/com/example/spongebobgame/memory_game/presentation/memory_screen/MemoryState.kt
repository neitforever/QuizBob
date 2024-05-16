package com.example.spongebobgame.memory_game.presentation.memory_screen

import com.example.spongebobgame.memory_game.domain.model.MemoryCard
import com.example.spongebobgame.memory_game.domain.util.generateCardsArray
import com.example.spongebobgame.memory_game.presentation.util.NumericValues.starting_pairs
import com.example.spongebobgame.ui.theme.SpongeBobTheme

data class MemoryState(
    var cards: Array<MemoryCard> = generateCardsArray(starting_pairs),
    val card1: Int? = null,
    val card2: Int? = null,
    val pairCount: Int = starting_pairs,
    val pairsMatched: Int = 0,
    val timeToWin: Long = 0L,
    val isGameWon: Boolean = false,
    val isGameStarted: Boolean = false,
    val startTime: Long = 0L,
    val currentTheme: SpongeBobTheme = SpongeBobTheme()
)

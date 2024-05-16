package com.example.spongebobgame.memory_game.domain.util

import com.example.spongebobgame.memory_game.domain.model.MemoryCard

fun generateCardsArray(matches: Int): Array<MemoryCard> {
    val singles = 1..matches
    val doubles = singles + singles
    return doubles.shuffled().map { MemoryCard(it)}.toTypedArray()
}
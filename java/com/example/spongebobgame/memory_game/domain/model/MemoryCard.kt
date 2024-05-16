package com.example.spongebobgame.memory_game.domain.model

class MemoryCard(
    var value: Int,
    var isBackDisplayed: Boolean = true,
    var matchFound: Boolean = false,
    ) {
    fun flipCard() {
        isBackDisplayed = !isBackDisplayed
    }
}
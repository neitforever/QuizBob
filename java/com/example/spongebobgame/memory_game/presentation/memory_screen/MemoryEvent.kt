package com.example.spongebobgame.memory_game.presentation.memory_screen

sealed class MemoryEvent {
    data class CardClick(val cardId: Int): MemoryEvent()
    object SetPairsToSix: MemoryEvent()
    object SetPairsToNine: MemoryEvent()
    object CloseGame: MemoryEvent()
    object ResetGame: MemoryEvent()
}
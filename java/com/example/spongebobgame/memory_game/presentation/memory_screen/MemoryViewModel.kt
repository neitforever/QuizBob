package com.example.spongebobgame.memory_game.presentation.memory_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spongebobgame.memory_game.domain.util.generateCardsArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemoryViewModel : ViewModel() {
    private val _state = mutableStateOf(MemoryState())
    val state: State<MemoryState> = _state
    private var delayedCompareJob: Job? = null

    fun onEvent(event: MemoryEvent) {
        when (event) {
            MemoryEvent.SetPairsToSix -> {
                setPairsToSix()
            }
            MemoryEvent.SetPairsToNine -> {
                setPairsToNine()
            }
            is MemoryEvent.CardClick -> {
                if (!_state.value.isGameStarted) {
                    startGameTimer()
                }
                onCardClick(event.cardId)
            }
            MemoryEvent.ResetGame -> {
                resetGame()
            }
            MemoryEvent.CloseGame -> {
                closeGame()
            }
        }
    }

    private fun startGameTimer() {
        if (!_state.value.isGameStarted) {
            val currentTime = System.currentTimeMillis()
            _state.value = _state.value.copy(startTime = currentTime, isGameStarted = true)
        }
    }

    private fun compareValues(first: Int?, second: Int?) {
        val cards = _state.value.cards.copyOf()
        if (second != null && first != null) {
            val card1 = cards[first]
            val card2 = cards[second]

            if (card1.value != card2.value) {
                cards[first].flipCard()
                cards[second].flipCard()
            } else {
                cards[first].matchFound = true
                cards[second].matchFound = true
                _state.value = _state.value.copy(
                    cards = cards,
                    pairsMatched = _state.value.pairsMatched + 1
                )
                if (_state.value.pairsMatched == _state.value.pairCount) {
                    onGameWon()
                }
            }
        }
        resetCompareCards()
    }

    private fun resetCompareCards() {
        if (_state.value.card2 != null) {
            _state.value = _state.value.copy(card1 = null, card2 = null)
        }
    }

    private fun cancelPreviousJob() {
        val firstIndex = _state.value.card1
        val secondIndex = _state.value.card2
        if (delayedCompareJob != null) {
            delayedCompareJob?.cancel()
            compareValues(firstIndex, secondIndex)
        }
    }

    private fun onGameWon() {
        if (!_state.value.isGameWon) {
            val currentTime = System.currentTimeMillis()
            val timeToWin = (currentTime - _state.value.startTime) / 1000
            _state.value = _state.value.copy(timeToWin = timeToWin, isGameWon = true)
        }
    }

    private fun onCardClick(id: Int) {
        cancelPreviousJob()
        val cards = _state.value.cards

        if (cards[id].isBackDisplayed) {
            delayedCompareJob = viewModelScope.launch(Dispatchers.IO) {
                flip(id)
                val firstIndex = _state.value.card1
                val secondIndex = _state.value.card2
                val bothCardsAreNotNull = firstIndex != null && secondIndex != null
                val cardsMatchSkipDelay =
                    if (bothCardsAreNotNull) cards[firstIndex!!].value == cards[secondIndex!!].value
                    else false
                if (!cardsMatchSkipDelay) {
                    delay(2000)
                }
                compareValues(firstIndex, secondIndex)
            }
        }
    }

    private fun flip(id: Int) {
        val cards = _state.value.cards.copyOf()
        cards[id].flipCard()
        val card2 = _state.value.card1
        _state.value = _state.value.copy(
            card1 = id,
            card2 = card2,
            cards = cards
        )
    }

    private fun setPairsToSix() {
        _state.value = _state.value.copy(pairCount = 6)
        resetGame()
    }

    private fun setPairsToNine() {
        _state.value = _state.value.copy(pairCount = 9)
        resetGame()
    }

    private fun resetGame() {
        _state.value = MemoryState(
            cards = generateCardsArray(_state.value.pairCount),
            pairCount = _state.value.pairCount
        )
    }

    fun closeGame() {
        println("Закрытие")
    }
}


package com.sopt.dive.viewModel

import androidx.lifecycle.ViewModel
import com.sopt.dive.model.CardState
import com.sopt.dive.model.FlipDirection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CardUiState(
    val currentCardState: CardState = CardState.BACK,
    val lastFlipDirection: FlipDirection = FlipDirection.LEFT
) {
    val isFaceUp: Boolean
        get() = currentCardState == CardState.FRONT
}

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _cardState = MutableStateFlow(CardUiState())
    val cardState = _cardState.asStateFlow()

    fun flipCard(direction: FlipDirection) {
        _cardState.update { currentState ->
            currentState.copy(
                currentCardState = currentState.currentCardState.next,
                lastFlipDirection = direction
            )
        }
    }
}
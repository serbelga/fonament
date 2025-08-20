package dev.sergiobelda.fonament.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class FonamentViewModel<U : FonamentUIState>(
    initialUIState: U,
) : ViewModel(), FonamentEventHandler {

    var uiState: U by mutableStateOf(initialUIState)
        private set

    override fun handleEvent(event: FonamentEvent) = Unit

    protected fun updateUIState(block: (state: U) -> U) {
        uiState = block.invoke(uiState)
    }
}

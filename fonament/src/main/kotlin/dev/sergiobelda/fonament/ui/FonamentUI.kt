package dev.sergiobelda.fonament.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

abstract class FonamentUI<U : FonamentUIState, C : FonamentContentState> {

    private lateinit var eventHandler: FonamentEventHandler

    @Composable
    abstract fun rememberContentState(
        uiState: U,
    ): C

    @Composable
    fun Content(
        viewModel: FonamentViewModel<U>,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        val contentState = rememberContentState(
            uiState = viewModel.uiState,
        )
        eventHandler = remember(contentState) {
            FonamentEventHandler { event ->
                viewModel.handleEvent(event)
                contentState.handleEvent(event)
                onEvent.invoke(event)
            }
        }
        Content(
            uiState = viewModel.uiState,
            contentState = contentState,
        )
    }

    @Composable
    protected abstract fun Content(
        uiState: U,
        contentState: C,
    )

    protected fun onEvent(
        event: FonamentEvent,
    ) {
        eventHandler.handleEvent(event)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FonamentUI<*, *>) return false

        return eventHandler == other.eventHandler
    }

    override fun hashCode(): Int =
        eventHandler.hashCode()
}

/*
 * Copyright 2025 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.sergiobelda.fonament.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

abstract class FonamentStatelessContent() : FonamentContent<NoUIState, NoUIElementState>() {

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {}
    ) {
        Content(
            uiState = NoUIState,
            uiElementState = NoUIElementState,
            modifier = modifier,
            onEvent = onEvent
        )
    }
}

/*abstract class FonamentStatelessUI(
    override val content: FonamentContent<NoUIState, NoUIElementState>
) : FonamentUI2<NoUIState, NoUIElementState>(
    content = content,
) {

    @Composable
    override fun createUIElementState(
        uiState: NoUIState,
    ): NoUIElementState = NoUIElementState
}*/

abstract class FonamentUI2<UIState : FonamentUIState, UIElementState : FonamentUIElementState>(
    protected open val content: FonamentContent<UIState, UIElementState>,
) {
    @Composable
    abstract fun createUIElementState(
        uiState: UIState,
    ): UIElementState

    @Composable
    fun Content(
        viewModel: FonamentViewModel<UIState>,
        uiElementState: UIElementState = createUIElementState(viewModel.uiState),
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        content.Content(
            uiState = viewModel.uiState,
            uiElementState = uiElementState,
            modifier = modifier,
            onEvent = {
                viewModel.handleEvent(it)
                onEvent.invoke(it)
            }
        )
    }
}

/*
interface FonamentUIElementStateFactory<UIElementState : FonamentUIElementState> {

    @Composable
    fun createUIElementState(): UIElementState
}
*/

abstract class FonamentContent<UIState : FonamentUIState, UIElementState : FonamentUIElementState> {

    private lateinit var eventHandler: FonamentEventHandler

    @Composable
    fun Content(
        uiState: UIState,
        uiElementState: UIElementState,
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {}
    ) {
        eventHandler = remember(uiElementState) {
            FonamentEventHandler { event ->
                uiElementState.handleEvent(event)
                onEvent.invoke(event)
            }
        }
        Content(
            uiState = uiState,
            uiElementState = uiElementState,
            modifier = modifier,
        )
    }

    @Composable
    protected abstract fun Content(
        uiState: UIState,
        uiElementState: UIElementState,
        modifier: Modifier,
    )

    protected fun onEvent(
        event: FonamentEvent,
    ) {
        eventHandler.handleEvent(event)
    }
}

/**
 *
 * @sample dev.sergiobelda.fonament.samples.SampleContent
 */
abstract class FonamentUI<U : FonamentUIState, C : FonamentUIElementState> {

    private lateinit var eventHandler: FonamentEventHandler

    @Composable
    abstract fun rememberUIElementState(
        uiState: U,
    ): C

    @Composable
    fun Content(
        viewModel: FonamentViewModel<U>,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        val contentState = rememberUIElementState(
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

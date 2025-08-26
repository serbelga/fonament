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

abstract class FonamentUI2<U : FonamentUIState>(
    private val content: FonamentContent<U, *>,
) {

    @Composable
    fun Content(
        viewModel: FonamentViewModel<U>,
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        content.Content(
            uiState = viewModel.uiState,
            modifier = modifier,
            onEvent = {
                viewModel.handleEvent(it)
                onEvent.invoke(it)
            }
        )
    }
}

abstract class FonamentContent<U : FonamentUIState, C : FonamentContentState> {

    private lateinit var eventHandler: FonamentEventHandler

    @Composable
    abstract fun rememberContentState(
        uiState: U,
    ): C

    @Composable
    fun Content(
        uiState: U,
        contentState: C = rememberContentState(uiState = uiState),
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        eventHandler = remember(contentState) {
            FonamentEventHandler { event ->
                contentState.handleEvent(event)
                onEvent.invoke(event)
            }
        }
        Content(
            uiState = uiState,
            contentState = contentState,
            modifier = modifier,
        )
    }

    @Composable
    protected abstract fun Content(
        uiState: U,
        contentState: C,
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

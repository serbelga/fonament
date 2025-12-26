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

package dev.sergiobelda.fonament.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Represents the base class for a UI Node (Screen, Dialog, ...).
 */
abstract class FonamentUI<U : FonamentUIState> {
    protected abstract val content: FonamentContent<U, *>

    @Composable
    operator fun invoke(
        viewModel: FonamentViewModel<U>,
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        content.invoke(
            uiState = viewModel.uiState,
            modifier = modifier,
            onEvent = {
                viewModel.handleEvent(it)
                onEvent.invoke(it)
            },
        )
    }

    override fun equals(other: Any?): Boolean = other is FonamentUI<*> && content == other.content

    override fun hashCode(): Int = content.hashCode()
}

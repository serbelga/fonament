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
 * A special type of [FonamentContent] that does not require a [FonamentUIState]
 * and [FonamentContentState] to render its content.
 */
abstract class FonamentStatelessContent : FonamentContent<NoUIState, NoContentState>() {

    @Composable
    override fun createContentState(uiState: NoUIState): NoContentState =
        NoContentState

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        onEvent: (FonamentEvent) -> Unit = {},
    ) {
        invoke(
            uiState = NoUIState,
            contentState = NoContentState,
            modifier = modifier,
            onEvent = onEvent,
        )
    }
}

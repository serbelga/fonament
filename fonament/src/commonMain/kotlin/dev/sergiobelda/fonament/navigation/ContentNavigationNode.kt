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

package dev.sergiobelda.fonament.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import dev.sergiobelda.fonament.ui.FonamentContent
import dev.sergiobelda.fonament.ui.FonamentUI
import dev.sergiobelda.fonament.ui.FonamentUIElementState
import dev.sergiobelda.fonament.ui.FonamentUIState
import dev.sergiobelda.fonament.ui.FonamentViewModel
import dev.sergiobelda.fonament.ui.FonamentStatelessContent
import dev.sergiobelda.fonament.ui.FonamentUI2

@Composable
inline fun <reified N, U> FonamentUI<U, *>.ContentNavigationNode(
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
    viewModel: FonamentViewModel<U>,
) where N : FonamentNavigationEvent, U : FonamentUIState {
    Content(
        viewModel = viewModel,
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}


@Composable
inline fun <reified N, U> FonamentUI2<U, *>.ContentNavigationNode(
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
    viewModel: FonamentViewModel<U>,
) where N : FonamentNavigationEvent, U : FonamentUIState {
    Content(
        viewModel = viewModel,
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

@Composable
inline fun <reified N, U, C> FonamentContent<U, C>.ContentNavigationNode(
    uiState: U,
    uiElementState: C,
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
) where N : FonamentNavigationEvent, U : FonamentUIState, C : FonamentUIElementState {
    Content(
        uiState = uiState,
        uiElementState = uiElementState,
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}


@Composable
inline fun <reified N> FonamentStatelessContent.ContentNavigationNode(
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
) where N : FonamentNavigationEvent {
    Content(
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

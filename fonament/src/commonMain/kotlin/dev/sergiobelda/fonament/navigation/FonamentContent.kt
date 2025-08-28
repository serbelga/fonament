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
import dev.sergiobelda.fonament.ui.FonamentContent
import dev.sergiobelda.fonament.ui.FonamentContentState
import dev.sergiobelda.fonament.ui.FonamentStatelessContent
import dev.sergiobelda.fonament.ui.FonamentUIState

@Composable
inline fun <reified N, U, C> FonamentContent<U, C>.NavigationNode(
    uiState: U,
    contentState: C,
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
) where N : FonamentNavigationEvent, U : FonamentUIState, C : FonamentContentState {
    invoke(
        uiState = uiState,
        contentState = contentState,
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

@Composable
inline fun <reified N> FonamentStatelessContent.NavigationNode(
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
) where N : FonamentNavigationEvent {
    invoke(
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

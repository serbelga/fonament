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

package dev.sergiobelda.fonament.presentation.navigation

import androidx.compose.runtime.Composable
import dev.sergiobelda.fonament.presentation.ui.FonamentUI
import dev.sergiobelda.fonament.presentation.ui.FonamentUIState
import dev.sergiobelda.fonament.presentation.ui.FonamentViewModel

/**
 * Wrapper of [FonamentUI.invoke] that receives a [FonamentNavigationEventHandler] and a [viewModel].
 * You can use this function to create a navigation entry node in your navigation graph.
 *
 * @param N The type of navigation event to handle, must implement [FonamentNavigationEvent].
 * @param U The type of UI state, must implement [FonamentUIState].
 *
 * @param navigationEventHandler The handler for navigation events of type [N]. Defaults to a no-op handler.
 * @param viewModel The [FonamentViewModel] providing the UI state of type [U].
 */
@Composable
inline fun <reified N, U> FonamentUI<U>.NavigationNode(
    navigationEventHandler: FonamentNavigationEventHandler<N> = FonamentNavigationEventHandler { },
    viewModel: FonamentViewModel<U>,
) where N : FonamentNavigationEvent, U : FonamentUIState {
    invoke(
        viewModel = viewModel,
        onEvent = {
            if (it is N) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

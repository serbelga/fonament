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

package dev.sergiobelda.fonament.di.koin

import androidx.compose.runtime.Composable
import dev.sergiobelda.fonament.navigation.FonamentNavigationEventHandler
import dev.sergiobelda.fonament.navigation.NavigationNodeContent
import dev.sergiobelda.fonament.ui.FonamentUI
import dev.sergiobelda.fonament.ui.FonamentUIState
import org.koin.core.parameter.ParametersDefinition

@Composable
inline fun <reified U> FonamentUI<U, *>.KoinNavigationNodeContent(
    noinline parameters: ParametersDefinition? = null,
    navigationEventHandler: FonamentNavigationEventHandler = FonamentNavigationEventHandler { },
) where U : FonamentUIState {
    NavigationNodeContent(
        viewModel = koinFonamentViewModel(parameters = parameters),
        navigationEventHandler = navigationEventHandler,
    )
}

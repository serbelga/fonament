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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Responsible of managing the [FonamentEvent]s and update the [uiState] that is observed
 * by the [FonamentUI].
 */
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

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

package dev.sergiobelda.fonament.demos.navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.sergiobelda.fonament.presentation.ui.FonamentStatelessContent
import dev.sergiobelda.fonament.presentation.ui.NoContentState
import dev.sergiobelda.fonament.presentation.ui.NoUIState
import kotlinx.serialization.Serializable

@Serializable
data object HomeContent : FonamentStatelessContent() {

    @Composable
    override fun Content(
        uiState: NoUIState,
        contentState: NoContentState,
        modifier: Modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primaryContainer),
        ) {
            Button(
                onClick = {
                    onEvent(HomeNavigationEvent.NavigateToDetails)
                },
                modifier = Modifier
                    .align(Alignment.Center),
            ) {
                Text("Navigate to Details")
            }
        }
    }
}

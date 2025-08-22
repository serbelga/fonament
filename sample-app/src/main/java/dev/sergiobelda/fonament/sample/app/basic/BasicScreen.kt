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

package dev.sergiobelda.fonament.sample.app.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sergiobelda.fonament.ui.FonamentUI

data object BasicScreen : FonamentUI<BasicUIState, BasicContentState>() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun rememberContentState(
        uiState: BasicUIState
    ): BasicContentState = rememberBasicContentState()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(
        uiState: BasicUIState,
        contentState: BasicContentState,
    ) {
        Scaffold { contentPadding ->
            if (contentState.showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { onEvent(BasicEvent.DismissBottomSheet) },
                    sheetState = contentState.sheetState,
                    modifier = Modifier.padding(contentPadding)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            IconButton(
                                onClick = { onEvent(BasicEvent.CloseBottomSheet) },
                            ) {
                                Icon(
                                    Icons.Rounded.Clear,
                                    contentDescription = "Close"
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onEvent(BasicEvent.DecreaseCounter) },
                                enabled = uiState.counter > 0
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                    contentDescription = "Decrease"
                                )
                            }
                            Text(
                                text = uiState.counter.toString(),
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            IconButton(
                                onClick = { onEvent(BasicEvent.IncreaseCounter) }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = "Increase"
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { onEvent(BasicEvent.OpenBottomSheet) },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Show bottom sheet")
                }
            }
        }
    }
}

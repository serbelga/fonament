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

package dev.sergiobelda.fonament.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sergiobelda.fonament.presentation.ui.FonamentContent
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalMaterial3Api::class)
data object SampleContent : FonamentContent<SampleUIState, SampleContentState>() {
    @Composable
    override fun createContentState(uiState: SampleUIState): SampleContentState = rememberSampleContentState()

    @Composable
    override fun Content(
        uiState: SampleUIState,
        contentState: SampleContentState,
        modifier: Modifier,
    ) {
        Scaffold(
            bottomBar = {
                BottomBar(::onEvent)
            },
        ) { paddingValues ->
            ContentList(
                lazyListState = contentState.lazyListState,
                list = uiState.list,
                modifier = Modifier.padding(paddingValues),
                onEvent = ::onEvent,
            )
        }
        if (contentState.showBottomSheet) {
            ModalBottomSheet(
                sheetState = contentState.sheetState,
                counter = uiState.counter,
                onEvent = ::onEvent,
            )
        }
    }
}

@Composable
private fun BottomBar(onEvent: (SampleEvent) -> Unit) {
    BottomAppBar(
        content = {
            IconButton(
                onClick = {
                    onEvent(SampleEvent.OpenBottomSheet)
                },
            ) {
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
private fun ContentList(
    lazyListState: LazyListState,
    modifier: Modifier,
    list: ImmutableList<SampleItemModel>,
    onEvent: (SampleEvent) -> Unit,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier,
    ) {
        items(
            list,
            key = { it.index },
            contentType = { it::class },
        ) { item ->
            ListItem(
                headlineContent = {
                    Text(text = item.text)
                },
                trailingContent = {
                    Checkbox(
                        checked = item.checked,
                        onCheckedChange = {
                            onEvent(SampleEvent.ItemChecked(item))
                        },
                    )
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalBottomSheet(
    sheetState: SheetState,
    counter: Int,
    onEvent: (SampleEvent) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onEvent(SampleEvent.DismissBottomSheet) },
        sheetState = sheetState,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = { onEvent(SampleEvent.CloseBottomSheet) },
                ) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "Close",
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { onEvent(SampleEvent.DecreaseCounter) },
                    enabled = counter > 0,
                ) {
                    Icon(
                        Icons.Rounded.Remove,
                        contentDescription = "Decrease",
                    )
                }
                Text(
                    text = "$counter",
                    style = MaterialTheme.typography.bodyLarge,
                )
                IconButton(
                    onClick = { onEvent(SampleEvent.IncreaseCounter) },
                ) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "Increase",
                    )
                }
            }
        }
    }
}

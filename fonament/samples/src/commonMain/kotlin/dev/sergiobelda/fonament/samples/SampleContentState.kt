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

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import dev.sergiobelda.fonament.ui.FonamentContentState
import dev.sergiobelda.fonament.ui.FonamentEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class SampleContentState(
    val lazyListState: LazyListState,
    val sheetState: SheetState,
    val coroutineScope: CoroutineScope,
) : FonamentContentState {

    var showBottomSheet by mutableStateOf(false)
        private set

    override fun handleEvent(event: FonamentEvent) {
        when (event) {
            SampleEvent.OpenBottomSheet -> { showBottomSheet = true }

            SampleEvent.DismissBottomSheet -> { showBottomSheet = false }

            SampleEvent.CloseBottomSheet -> {
                coroutineScope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }
        }
    }

    companion object {

        fun saver(
            lazyListState: LazyListState,
            sheetState: SheetState,
            coroutineScope: CoroutineScope,
        ): Saver<SampleContentState, Boolean> = Saver(
            save = {
                it.showBottomSheet
            },
            restore = {
                SampleContentState(
                    lazyListState = lazyListState,
                    sheetState = sheetState,
                    coroutineScope = coroutineScope,
                ).apply {
                    showBottomSheet = it
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberSampleContentState(
    lazyListState: LazyListState = rememberLazyListState(),
    sheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): SampleContentState =
    rememberSaveable(
        saver = SampleContentState.saver(
            lazyListState = lazyListState,
            sheetState = sheetState,
            coroutineScope = coroutineScope,
        ),
    ) {
        SampleContentState(
            lazyListState = lazyListState,
            sheetState = sheetState,
            coroutineScope = coroutineScope,
        )
    }

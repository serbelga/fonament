package dev.sergiobelda.fonament.sample.app.basic

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.sergiobelda.fonament.ui.FonamentContentState
import dev.sergiobelda.fonament.ui.FonamentEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Stable
data class BasicContentState(
    val sheetState: SheetState,
    val coroutineScope: CoroutineScope,
) : FonamentContentState {

    var showBottomSheet by mutableStateOf(false)
        private set

    override fun handleEvent(event: FonamentEvent) {
        when (event) {
            BasicEvent.OpenBottomSheet -> {
                showBottomSheet = true
            }

            BasicEvent.DismissBottomSheet -> {
                showBottomSheet = false
            }

            BasicEvent.CloseBottomSheet -> {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBasicContentState(
    sheetState: SheetState = rememberModalBottomSheetState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): BasicContentState =
    remember {
        BasicContentState(
            sheetState = sheetState,
            coroutineScope = coroutineScope,
        )
    }

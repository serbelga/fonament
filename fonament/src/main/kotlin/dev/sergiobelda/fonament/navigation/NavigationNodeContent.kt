package dev.sergiobelda.fonament.navigation

import androidx.compose.runtime.Composable
import dev.sergiobelda.fonament.ui.FonamentUI
import dev.sergiobelda.fonament.ui.FonamentUIState
import dev.sergiobelda.fonament.ui.FonamentViewModel

@Composable
fun <U> FonamentUI<U, *>.NavigationNodeContent(
    viewModel: FonamentViewModel<U>,
    navigationEventHandler: FonamentNavigationEventHandler = FonamentNavigationEventHandler { },
) where U : FonamentUIState {
    Content(
        viewModel = viewModel,
        onEvent = {
            if (it is FonamentNavigationEvent) {
                navigationEventHandler.handleNavigationEvent(it)
            }
        },
    )
}

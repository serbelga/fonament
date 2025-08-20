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

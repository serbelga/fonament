package dev.sergiobelda.fonament.di.koin

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import dev.sergiobelda.fonament.ui.FonamentUIState
import dev.sergiobelda.fonament.ui.FonamentViewModel
import org.koin.compose.currentKoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.viewmodel.defaultExtras

/**
 * Allow to declare a [FonamentViewModel] - be later inject with dedicated injector.
 *
 * @param definition - allow definition override
 */
inline fun <reified U : FonamentUIState> Module.fonamentViewModel(
    noinline definition: Definition<FonamentViewModel<U>>,
): KoinDefinition<FonamentViewModel<U>> = viewModel(named<U>(), definition)

@OptIn(KoinInternalApi::class)
@Composable
inline fun <reified U : FonamentUIState> koinFonamentViewModel(
    viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"),
    key: String? = null,
    extras: CreationExtras = defaultExtras(viewModelStoreOwner),
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null,
): FonamentViewModel<U> =
    koinViewModel(
        qualifier = named<U>(),
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        extras = extras,
        scope = scope,
        parameters = parameters,
    )

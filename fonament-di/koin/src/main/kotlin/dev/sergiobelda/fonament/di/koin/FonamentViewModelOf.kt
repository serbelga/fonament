package dev.sergiobelda.fonament.di.koin

import dev.sergiobelda.fonament.ui.FonamentUIState
import dev.sergiobelda.fonament.ui.FonamentViewModel
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.new
import org.koin.core.module.dsl.onOptions

/**
 * Declare a [Module.fonamentViewModel] definition by resolving a constructor reference for the dependency.
 * The resolution is done at compile time by leveraging inline functions, no reflection is required.
 *
 * ```kotlin
 * class AboutViewModel : FonamentViewModel<AboutUIState>(initialUIState = AboutUIState)
 *
 * val aboutViewModelModule = module {
 *      fonamentViewModelOf(::AboutViewModel)
 * }
 * ```
 */
inline fun <reified U : FonamentUIState> Module.fonamentViewModelOf(
    crossinline constructor: () -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1> Module.fonamentViewModelOf(
    crossinline constructor: (T1) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

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

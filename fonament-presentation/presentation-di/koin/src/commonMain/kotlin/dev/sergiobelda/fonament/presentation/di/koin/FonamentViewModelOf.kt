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

@file:Suppress("ktlint:standard:max-line-length")

package dev.sergiobelda.fonament.presentation.di.koin

import dev.sergiobelda.fonament.presentation.ui.FonamentUIState
import dev.sergiobelda.fonament.presentation.ui.FonamentViewModel
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

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19> Module.fonamentViewModelOf(
    crossinline constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19, reified T20> Module.fonamentViewModelOf(
    crossinline constructor: (
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
    ) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19, reified T20, reified T21> Module.fonamentViewModelOf(
    crossinline constructor: (
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21,
    ) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

inline fun <reified U : FonamentUIState, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19, reified T20, reified T21, reified T22> Module.fonamentViewModelOf(
    crossinline constructor: (
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21,
        T22,
    ) -> FonamentViewModel<U>,
    noinline options: (BeanDefinition<FonamentViewModel<U>>.() -> Unit)? = null,
): KoinDefinition<FonamentViewModel<U>> = fonamentViewModel { new(constructor) }.onOptions(options)

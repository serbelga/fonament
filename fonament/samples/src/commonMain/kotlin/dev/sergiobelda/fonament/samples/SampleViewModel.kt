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

import dev.sergiobelda.fonament.ui.FonamentEvent
import dev.sergiobelda.fonament.ui.FonamentViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

private val sampleData = List(50) {
    SampleItemModel(
        index = it,
        text = "Item $it",
        checked = it % 5 == 0,
    )
}.toPersistentList()

open class SampleViewModel(
    list: ImmutableList<SampleItemModel> = sampleData,
    counter: Int = 0,
) : FonamentViewModel<SampleUIState>(
    initialUIState = SampleUIState(
        list = list,
        counter = counter,
    ),
) {

    override fun handleEvent(event: FonamentEvent) {
        when (event) {
            is SampleEvent.ItemChecked -> {
                updateUIState {
                    it.copy(
                        list = it.list.map { item ->
                            if (item == event.item) {
                                item.copy(checked = !item.checked)
                            } else {
                                item
                            }
                        }.toPersistentList(),
                    )
                }
            }

            is SampleEvent.IncreaseCounter -> {
                updateUIState {
                    it.copy(
                        counter = it.counter + 1,
                    )
                }
            }

            is SampleEvent.DecreaseCounter -> {
                updateUIState {
                    it.copy(
                        counter = (it.counter - 1).coerceAtLeast(0),
                    )
                }
            }
        }
    }
}

class SampleViewModelV2 : SampleViewModel(
    list = sampleData.take(10).toPersistentList(),
    counter = 100,
)

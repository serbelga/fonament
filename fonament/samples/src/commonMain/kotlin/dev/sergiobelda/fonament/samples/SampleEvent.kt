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

sealed interface SampleEvent : FonamentEvent {
    data class ItemChecked(
        val item: SampleItemModel,
    ) : SampleEvent

    data object IncreaseCounter : SampleEvent

    data object DecreaseCounter : SampleEvent

    data object OpenBottomSheet : SampleEvent

    data object CloseBottomSheet : SampleEvent

    data object DismissBottomSheet : SampleEvent
}

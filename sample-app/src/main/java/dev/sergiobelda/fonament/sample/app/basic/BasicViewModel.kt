package dev.sergiobelda.fonament.sample.app.basic

import dev.sergiobelda.fonament.ui.FonamentEvent
import dev.sergiobelda.fonament.ui.FonamentViewModel

open class BasicViewModel(
    initialCounter: Int = 0,
) : FonamentViewModel<BasicUIState>(
    initialUIState = BasicUIState(
        counter = initialCounter,
    ),
) {

    override fun handleEvent(event: FonamentEvent) {
        when (event) {
            is BasicEvent.IncreaseCounter -> {
                updateUIState {
                    it.copy(
                        counter = it.counter + 1,
                    )
                }
            }

            is BasicEvent.DecreaseCounter -> {
                updateUIState {
                    it.copy(
                        counter = (it.counter - 1).coerceAtLeast(0),
                    )
                }
            }
        }
    }
}

class BasicViewModelV2() : BasicViewModel(initialCounter = 100)

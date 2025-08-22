package dev.sergiobelda.fonament.sample.app.basic

import dev.sergiobelda.fonament.ui.FonamentEvent

sealed class BasicEvent : FonamentEvent {

    data object OpenBottomSheet : BasicEvent()

    data object CloseBottomSheet : BasicEvent()

    data object DismissBottomSheet : BasicEvent()

    data object IncreaseCounter : BasicEvent()

    data object DecreaseCounter : BasicEvent()
}

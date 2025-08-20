package dev.sergiobelda.fonament.ui

interface FonamentContentState : FonamentEventHandler {
    override fun handleEvent(event: FonamentEvent) = Unit

    companion object : FonamentContentState
}

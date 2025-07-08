package com.ee.kmp.ui.flows.login

import com.ee.kmp.data.Breed
import com.ee.kmp.ui.actions.SystemAction

sealed class BreedAction {
    data class OnBreedSelected(val breed: Breed, val onSystemAction: (SystemAction) -> Unit): BreedAction()
    object OnLoadBreeds: BreedAction()
}
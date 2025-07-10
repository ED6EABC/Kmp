package com.ee.kmp.ui.flows.breedList.model

import com.ee.kmp.data.model.Breed
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.flows.breedList.BreedDetail

sealed class BreedAction {
    data class OnBreedSelected(val breed: Breed, val onSystemAction: (SystemAction) -> Unit): BreedAction()
    object OnLoadBreeds: BreedAction()
    data class OnSaveAsFavorite(val breedDetail: BreedDetail): BreedAction()
}
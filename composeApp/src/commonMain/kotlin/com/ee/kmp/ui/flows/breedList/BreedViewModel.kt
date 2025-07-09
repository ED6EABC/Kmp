package com.ee.kmp.ui.flows.breedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.data.model.Breed
import com.ee.kmp.domine.useCases.GetBreedsUseCase
import com.ee.kmp.domine.useCases.RemoveFavoriteUseCase
import com.ee.kmp.domine.useCases.SaveFavoriteUseCase
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.actions.SystemAction.*
import com.ee.kmp.ui.flows.login.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModel() {

    init { onAction(BreedAction.OnLoadBreeds) }

    var state = MutableStateFlow<List<Breed>?>(listOf())
        private set

    var breedSelected = MutableStateFlow<BreedDetail?>(null)
        private set

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val content = getBreedsUseCase.invoke()
            state.update { content }
        }
    }

    private fun saveFavorite(breedDetail: BreedDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            if(breedDetail.isFavorite) {
                removeFavoriteUseCase.invoke(breedDetail.breed.id)
                breedSelected.update { breedDetail.copy(isFavorite = false) }
            } else {
                saveFavoriteUseCase.invoke(breedDetail.breed)
                breedSelected.update { breedDetail.copy(isFavorite = true) }
            }
        }
    }

    fun onAction(action: BreedAction) {
        when(action){
            is BreedAction.OnBreedSelected -> {
                breedSelected.update { BreedDetail(action.breed) }
                action.onSystemAction(Navigate(Routes.BreedDetail))
            }
            BreedAction.OnLoadBreeds -> getData()
            is BreedAction.OnSaveAsFavorite -> saveFavorite(action.breedDetail)
        }
    }
}

data class BreedDetail(
    var breed: Breed,
    var isFavorite: Boolean = false
)
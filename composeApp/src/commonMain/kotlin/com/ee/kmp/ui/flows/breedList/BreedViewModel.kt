package com.ee.kmp.ui.flows.breedList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.data.model.Breed
import com.ee.kmp.domine.useCases.FindFavoriteUseCase
import com.ee.kmp.domine.useCases.GetBreedsUseCase
import com.ee.kmp.domine.useCases.RemoveFavoriteUseCase
import com.ee.kmp.domine.useCases.SaveFavoriteUseCase
import com.ee.kmp.ui.actions.SystemAction.*
import com.ee.kmp.ui.flows.login.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val findFavoriteUseCase: FindFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    var uiState = _uiState.asStateFlow()

    var breedSelected = MutableStateFlow<BreedDetail?>(null)
        private set

    init { onAction(BreedAction.OnLoadBreeds) }

    private fun getData() {
        setLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            val content = getBreedsUseCase.invoke(_uiState.value.page, 10).toMutableList()

            val newContent = _uiState.value.breeds
            newContent.addAll(content)

            _uiState.update {
                it.copy(
                    breeds = newContent,
                    page = it.page + 1,
                    isError = false
                )
            }
            setLoader(false)
        }
    }

    private fun setLoader(state: Boolean) {
        _uiState.update { it.copy(isLoading = state) }
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

    private fun onBreedSelected(breed: Breed) {
        val isFavorite = findFavoriteUseCase.invoke(breed.id).executeAsOne()
        breedSelected.update { BreedDetail(breed, isFavorite) }
    }

    fun onAction(action: BreedAction) {
        when(action){
            is BreedAction.OnBreedSelected -> {
                onBreedSelected(action.breed)
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

data class UiState(
    var breeds: MutableList<Breed> = mutableListOf(),
    var isLoading: Boolean = true,
    var page: Int = 0,
    var isError: Boolean = false
)
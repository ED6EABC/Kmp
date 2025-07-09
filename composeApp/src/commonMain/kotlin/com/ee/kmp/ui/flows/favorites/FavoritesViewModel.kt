package com.ee.kmp.ui.flows.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.data.model.Breed
import com.ee.kmp.domine.useCases.GetFavoritesUseCase
import com.ee.kmp.domine.useCases.RemoveFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
): ViewModel() {

    var state = MutableStateFlow<List<Breed>>(listOf())
        private set

    init { onAction(FavoritesAction.GetFavorites) }

    private fun getFavorites() {
        viewModelScope.launch {
            state.update { getFavoritesUseCase.invoke() }
        }
    }

    private fun removeFavorite(breedId: String) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(breedId)
            getFavorites()
        }
    }

    fun onAction(action: FavoritesAction) {
        when(action) {
            FavoritesAction.GetFavorites -> getFavorites()
            is FavoritesAction.RemoveFavorite -> removeFavorite(action.breedId)
        }
    }

}

sealed class FavoritesAction {
    object GetFavorites: FavoritesAction()
    data class RemoveFavorite(val breedId: String): FavoritesAction()
}
package com.ee.kmp.ui.flows.breedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.data.Breed
import com.ee.kmp.domine.useCases.GetBreedsUseCase
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.flows.login.BreedAction
import com.ee.kmp.ui.navigation.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BreedViewModel(
    private val getBreedsUseCase: GetBreedsUseCase
): ViewModel() {

    init { onAction(BreedAction.OnLoadBreeds) }

    var state = MutableStateFlow<List<Breed>?>(listOf())
        private set

    var breedSelected = MutableStateFlow<Breed?>(null)
        private set

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val content = getBreedsUseCase.invoke()
            state.update { content }
        }
    }

    fun onAction(action: BreedAction) {
        when(action){
            is BreedAction.OnBreedSelected -> {
                breedSelected.update { action.breed }
                action.onSystemAction(SystemAction.Navigate(Routes.BreedDetail))
            }
            BreedAction.OnLoadBreeds -> getData()
        }
    }
}
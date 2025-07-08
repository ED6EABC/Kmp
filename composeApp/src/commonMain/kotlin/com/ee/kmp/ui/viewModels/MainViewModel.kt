package com.ee.kmp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.data.Breed
import com.ee.kmp.domine.useCases.GetBreedsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBreedsUseCase: GetBreedsUseCase
): ViewModel() {

    init {
        getData()
    }

    var state = MutableStateFlow<List<Breed>?>(listOf())
        private set

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val content = getBreedsUseCase.invoke()
            state.update { content }
        }
    }
}
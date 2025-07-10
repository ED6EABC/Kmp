package com.ee.kmp.ui.flows.splash

import androidx.lifecycle.ViewModel
import com.ee.kmp.domine.useCases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init { validateSession() }

    private fun setLoaderState(state: Boolean) {
        _uiState.update { it.copy(isLoading = state) }
    }

    private fun validateSession() {
        setLoaderState(true)
        _uiState.update { AppUiState(isUserLogged = loginUseCase.invoke().executeAsOne()) }
        setLoaderState(false)
    }

}

data class AppUiState(
    var isUserLogged: Boolean? = null,
    var isLoading: Boolean = true
)
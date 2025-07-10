package com.ee.kmp.ui.flows.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ee.kmp.domine.useCases.CreateUserUseCase
import com.ee.kmp.ui.actions.CustomNavOptions
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.flows.login.model.InputState
import com.ee.kmp.ui.flows.login.model.LoginAction
import com.ee.kmp.ui.flows.login.model.LoginUiState
import com.ee.kmp.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val createUserUseCase: CreateUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private fun createUser(username: String, password: String, onCompleted: () -> Unit) {
        setLoadingState(true)
        viewModelScope.launch {
            createUserUseCase.invoke(username, password)
            setLoadingState(false)
            onCompleted()
        }
    }

    private fun setLoadingState(state: Boolean) {
        _uiState.update { it.copy(isLoading = state) }
    }

    fun onAction(action: LoginAction) {
        when(action){
            is LoginAction.Login -> {
                createUser(_uiState.value.username.value, _uiState.value.userPassword.value) {
                    action.onSystemAction(
                        SystemAction.Navigate(
                            route = Routes.BreedsList,
                            CustomNavOptions(Routes.Login)
                        )
                    )
                }
            }
            is LoginAction.PasswordChanged -> _uiState.update { _uiState.value.copy( userPassword = action.password ) }
            is LoginAction.UsernameChanged -> _uiState.update { _uiState.value.copy( username = action.name ) }
        }
    }

}
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
import com.ee.kmp.ui.utils.isValidPassword
import com.ee.kmp.ui.utils.isValidUsername
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
            is LoginAction.PasswordChanged -> _uiState.update {
                val isError = action.password.value.isEmpty() || !isValidPassword(action.password.value)
                _uiState.value.copy( userPassword = InputState(action.password.value, isError))
            }
            is LoginAction.UsernameChanged -> _uiState.update {
                val isError = action.name.value.isEmpty() || !isValidUsername(action.name.value)
                _uiState.value.copy( username = InputState(action.name.value, isError))
            }
        }
    }

}
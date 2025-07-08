package com.ee.kmp.ui.flows.login

import androidx.lifecycle.ViewModel
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(

): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginAction) {
        when(action){
            is LoginAction.Login -> {
                println("Try to login with ${_state.value.username.value} and ${_state.value.userPassword.value}")
                action.onSystemAction(SystemAction.Navigate(Routes.BreedsList))
            }
            is LoginAction.PasswordChanged -> _state.update { _state.value.copy( userPassword = action.password ) }
            is LoginAction.UsernameChanged -> _state.update { _state.value.copy( username = action.name ) }
        }
    }

}
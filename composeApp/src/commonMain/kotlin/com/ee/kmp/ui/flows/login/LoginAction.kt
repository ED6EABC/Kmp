package com.ee.kmp.ui.flows.login

import com.ee.kmp.ui.actions.SystemAction

sealed class LoginAction {
    data class UsernameChanged(val name: InputState<String>) : LoginAction()
    data class PasswordChanged(val password: InputState<String>) : LoginAction()
    data class Login(val onSystemAction: (SystemAction) -> Unit) : LoginAction()
}
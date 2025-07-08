package com.ee.kmp.ui.flows.login

data class LoginState(
    var username: InputState<String> = InputState(""),
    var userPassword: InputState<String> = InputState("")
)

data class InputState<out T>(
    val value: T,
    val isError: Boolean = false
)
package com.ee.kmp.ui.flows.login.model

data class LoginUiState(
    var username: InputState<String> = InputState(""),
    var userPassword: InputState<String> = InputState(""),
    var isError: Boolean = false,
    var isLoading: Boolean = false
)

data class InputState<out T>(
    val value: T,
    val isError: Boolean = false
)
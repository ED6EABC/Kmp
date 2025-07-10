package com.ee.kmp.ui.flows.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.Loader
import com.ee.kmp.ui.flows.login.model.InputState
import com.ee.kmp.ui.flows.login.model.LoginAction
import kmp.composeapp.generated.resources.Login
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.nameLabel
import kmp.composeapp.generated.resources.passwordLabel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
private fun LoginPreview() {
    Login{}
}

@Composable
fun Login(onSystemAction: (SystemAction) -> Unit) {

    val loginViewModel = koinViewModel<LoginViewModel>()
    val uiState by loginViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(18.dp))
        TextField(
            value = uiState.username.value,
            onValueChange = {
                loginViewModel.onAction(LoginAction.UsernameChanged(InputState(it, it.isEmpty())))
            },
            label = {
                Text(stringResource(Res.string.nameLabel))
            },
            isError = uiState.username.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(18.dp))
        TextField(
            value = uiState.userPassword.value,
            onValueChange = {
                loginViewModel.onAction(LoginAction.PasswordChanged(InputState(it, it.isEmpty())))
            },
            label = {
                Text(stringResource(Res.string.passwordLabel))
            },
            isError = uiState.userPassword.isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.weight(1f))

        TextButton(
            onClick = {
                loginViewModel.onAction(
                    LoginAction.Login(onSystemAction)
                )
            },
           modifier = Modifier.height(60.dp)
        ) {
            Text(text = stringResource(Res.string.Login))
        }
        Spacer(Modifier.height(18.dp))

        Loader(uiState.isLoading)
    }
}
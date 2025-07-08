package com.ee.kmp.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kmp.composeapp.generated.resources.Login
import kmp.composeapp.generated.resources.Res
import kmp.composeapp.generated.resources.nameLabel
import kmp.composeapp.generated.resources.passwordLabel
import org.jetbrains.compose.resources.stringResource

@Composable
private fun LoginPreview() {
    Login()
}

@Composable
fun Login(

) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(18.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text(stringResource(Res.string.nameLabel))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(18.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(stringResource(Res.string.passwordLabel))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.weight(1f))

        TextButton(
            onClick = { /*TODO*/ },
           modifier = Modifier.height(60.dp)
        ) {
            Text(text = stringResource(Res.string.Login))
        }
        Spacer(Modifier.height(18.dp))
    }
}
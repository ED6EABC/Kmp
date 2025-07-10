package com.ee.kmp.ui.flows.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ee.kmp.ui.actions.CustomNavOptions
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.Loader
import com.ee.kmp.ui.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Splash(onSystemAction: (SystemAction) -> Unit) {

    val splashViewModel = koinViewModel<SplashViewModel>()
    val uiState by splashViewModel.uiState.collectAsState()

    Loader(uiState.isLoading)

    LaunchedEffect(uiState.isUserLogged) {
        val route: Routes? = when(uiState.isUserLogged) {
            true -> Routes.BreedsList
            false -> Routes.Login
            null -> null
        }

        route?.let {
            onSystemAction(
                SystemAction.Navigate(
                    route,
                    CustomNavOptions(Routes.Splash)
                )
            )
        }
    }
}
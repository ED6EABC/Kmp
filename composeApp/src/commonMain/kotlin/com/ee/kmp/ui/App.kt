package com.ee.kmp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ee.kmp.ui.actions.SystemAction
import com.ee.kmp.ui.composables.Loader
import com.ee.kmp.ui.flows.splash.SplashViewModel
import com.ee.kmp.ui.navigation.NavGraph
import com.ee.kmp.ui.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {

    val navController = rememberNavController()

    NavGraph(navController) { systemAction ->
        when(systemAction) {
            is SystemAction.Navigate -> {
                navController.navigate(systemAction.route.path) {
                    systemAction.navOptions?.dropBackStackFrom?.path?.let { path ->
                        popUpTo(path) {
                            inclusive = true
                        }
                    }
                }
            }
            SystemAction.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }
}
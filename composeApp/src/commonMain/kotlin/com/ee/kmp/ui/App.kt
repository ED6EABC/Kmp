package com.ee.kmp.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.ee.kmp.ui.navigation.NavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    Scaffold { padding ->
        NavGraph(padding, navController)
    }
}
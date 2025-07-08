package com.ee.kmp.ui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ee.kmp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kmp",
    ) {
        App()
    }

    initKoin {}
}
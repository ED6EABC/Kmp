package com.ee.kmp.ui.actions

import com.ee.kmp.ui.navigation.Routes

sealed class SystemAction {
    data class Loader(val isLoading: Boolean): SystemAction()
    data class Navigate(val route: Routes): SystemAction()
}
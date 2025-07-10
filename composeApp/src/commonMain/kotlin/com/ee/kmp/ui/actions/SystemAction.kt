package com.ee.kmp.ui.actions

import com.ee.kmp.ui.navigation.Routes

sealed class SystemAction {
    data class Navigate(
        val route: Routes,
        val navOptions: CustomNavOptions? = null
    ): SystemAction()
    object NavigateBack: SystemAction()
}

data class CustomNavOptions(
    val dropBackStackFrom: Routes,
)
package com.ee.kmp.ui.utils

fun isValidUsername(username: String): Boolean {
    val usernameRegex = Regex("^[a-zA-Z]+$")
    return usernameRegex.matches(username)
}
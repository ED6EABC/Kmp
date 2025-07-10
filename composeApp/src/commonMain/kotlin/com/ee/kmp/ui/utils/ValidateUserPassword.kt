package com.ee.kmp.ui.utils

fun isValidPassword(username: String): Boolean {
    val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\S]{6,10}$")
    return passwordRegex.matches(username)
}
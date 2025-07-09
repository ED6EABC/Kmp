package com.ee.kmp.data.remote

const val Host = "https://api.thecatapi.com/v1"

sealed class APIs(val url: String) {
    object Breeds: APIs("$Host/breeds")
}
package com.ee.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
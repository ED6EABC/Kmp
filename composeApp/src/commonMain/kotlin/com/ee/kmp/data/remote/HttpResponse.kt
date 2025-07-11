package com.ee.kmp.data.remote

sealed class HttpResponse<out R> {
    data class Success<T>(val data: T): HttpResponse<T>()
    data class Error(val code: Int, val message: String) : HttpResponse<Nothing>()
    object Exception : HttpResponse<Nothing>()
}
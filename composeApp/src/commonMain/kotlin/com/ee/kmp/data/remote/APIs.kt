package com.ee.kmp.data.remote

import com.ee.kmp.data.model.Breed

const val Host = "https://api.thecatapi.com/v1"

sealed class APIs(val url: String) {
    object Breeds: APIs("$Host/breeds") {
        data class Request(
            val page: Int,
            val limit: Int
        )

        data class Response(
            val breeds: List<Breed>,
            val paginationCount: Int
        )
    }
}
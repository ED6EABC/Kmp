package com.ee.kmp.domine

import com.ee.kmp.data.remote.APIs
import com.ee.kmp.data.remote.HttpResponse

interface MainRepository {
    suspend fun getData(request: APIs.Breeds.Request): HttpResponse<APIs.Breeds.Response>
}
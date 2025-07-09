package com.ee.kmp.domine

import com.ee.kmp.data.remote.APIs

interface MainRepository {
    suspend fun getData(request: APIs.Breeds.Request): APIs.Breeds.Response
}
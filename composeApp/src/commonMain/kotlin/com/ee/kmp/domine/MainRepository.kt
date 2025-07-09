package com.ee.kmp.domine

import com.ee.kmp.data.model.Breed

interface MainRepository {
    suspend fun getData(page: Int, limit: Int): List<Breed>
}
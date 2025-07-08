package com.ee.kmp.domine

import com.ee.kmp.data.Breed

interface MainRepository {
    suspend fun getData(): List<Breed>?
}
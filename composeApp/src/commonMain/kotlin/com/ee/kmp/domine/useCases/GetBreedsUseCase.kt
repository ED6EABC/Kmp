package com.ee.kmp.domine.useCases

import com.ee.kmp.data.remote.APIs
import com.ee.kmp.domine.MainRepository

class GetBreedsUseCase(
    private val mainRepository: MainRepository
) {
    suspend fun invoke(request: APIs.Breeds.Request) = mainRepository.getData(request)
}
package com.ee.kmp.domine.useCases

import com.ee.kmp.domine.MainRepository

class GetBreedsUseCase(
    private val mainRepository: MainRepository
) {
    suspend fun invoke(page: Int, limit: Int) = mainRepository.getData(page, limit)
}
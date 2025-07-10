package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

class RemoveFavoriteUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    suspend fun invoke(breedId: String) = breedsDataBase.breedTableQueries.removeFavoriteBreed(breedId)
}
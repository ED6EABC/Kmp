package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

class RemoveFavoriteUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    fun invoke(breedId: String) = breedsDataBase.breedsTableQueries.removeFavoriteBreed(breedId)
}
package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

class FindFavoriteUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    fun invoke(breedId: String) = breedsDataBase.breedsDataBaseQueries.isFavorite(breedId)
}
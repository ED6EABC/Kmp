package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

class LoginUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    fun invoke() = breedsDataBase.usersTableQueries.isUserLogged()
}
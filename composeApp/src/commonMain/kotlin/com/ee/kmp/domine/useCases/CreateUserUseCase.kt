package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

class CreateUserUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    suspend fun invoke(username: String, password: String) = breedsDataBase.usersTableQueries.createUser(username, password)
}
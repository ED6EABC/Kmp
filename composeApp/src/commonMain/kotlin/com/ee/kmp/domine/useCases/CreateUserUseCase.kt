package com.ee.kmp.domine.useCases

import com.breeds.BreedsDataBase

const val KEY_INDEX = 1L

class CreateUserUseCase(
    private val breedsDataBase: BreedsDataBase
) {
    suspend fun invoke(username: String, password: String) = breedsDataBase.usersTableQueries.createUser(KEY_INDEX, username, password)
}
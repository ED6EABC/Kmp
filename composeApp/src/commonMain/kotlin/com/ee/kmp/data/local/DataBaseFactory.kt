package com.ee.kmp.data.local

import app.cash.sqldelight.db.SqlDriver
import com.breeds.BreedsDataBase

class DataBaseFactory(
    private val driver: SqlDriver
) {
    fun invoke() = BreedsDataBase(driver)
}
package com.ee.kmp.data.local

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.breeds.BreedsDataBase

class AndroidDataBaseDriverFactory(
    private val context: Context
): DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = BreedsDataBase.Schema.synchronous(),
            context = context,
            name = "BreedsDataBase.db",
        )
    }
}


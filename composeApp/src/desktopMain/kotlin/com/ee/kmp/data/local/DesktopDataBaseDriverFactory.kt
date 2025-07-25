package com.ee.kmp.data.local

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.breeds.BreedsDataBase
import java.util.Properties

class DesktopDataBaseDriverFactory(): DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return  JdbcSqliteDriver("jdbc:sqlite:BreedsDataBase.db", Properties(), BreedsDataBase.Schema.synchronous())
    }
}
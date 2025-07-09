package com.ee.kmp.di

import app.cash.sqldelight.db.SqlDriver
import com.breeds.BreedsDataBase
import com.ee.kmp.data.local.DataBaseFactory
import com.ee.kmp.data.local.DesktopDataBaseDriverFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformDataModule = module {
    single<HttpClient> {
        HttpClient(Java) {
            install(ContentNegotiation) {
                json(
                    Json { ignoreUnknownKeys = true }
                )
            }
            install(Logging) {
                logger = Logger.SIMPLE
            }
        }
    }
    single<SqlDriver> { DesktopDataBaseDriverFactory().createDriver() }
}

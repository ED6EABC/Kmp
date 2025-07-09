package com.ee.kmp.di

import app.cash.sqldelight.db.SqlDriver
import com.ee.kmp.data.local.AndroidDataBaseDriverFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE

actual val platformDataModule = module {
    single<HttpClient>{
        HttpClient(OkHttp) {
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

    single<SqlDriver> { AndroidDataBaseDriverFactory(get()).createDriver() }
}
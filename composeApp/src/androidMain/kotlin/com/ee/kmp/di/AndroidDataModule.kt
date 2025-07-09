package com.ee.kmp.di

import app.cash.sqldelight.db.SqlDriver
import com.ee.kmp.data.local.AndroidDataBaseDriverFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

actual val platformDataModule = module {
    single<HttpClientEngine> {
        HttpClient(OkHttp.create()) {
            install(ContentNegotiation) {
                json(
                    Json { ignoreUnknownKeys = true }
                )
            }
        }.engine
    }

    single<SqlDriver> { AndroidDataBaseDriverFactory(get()).createDriver() }
}
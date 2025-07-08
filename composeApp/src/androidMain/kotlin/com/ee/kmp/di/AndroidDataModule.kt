package com.ee.kmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

actual val platformDataModule = module {
    single<HttpClientEngine> {
        HttpClient(OkHttp.create()) {
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
            }
        }.engine
    }
}

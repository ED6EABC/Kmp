package com.ee.kmp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

actual val platformDataModule = module {
    single<HttpClientEngine> {
        HttpClient(Java) {
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
            }
        }.engine
    }
}

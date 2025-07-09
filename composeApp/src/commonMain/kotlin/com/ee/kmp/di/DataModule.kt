package com.ee.kmp.di

import com.breeds.BreedsDataBase
import com.ee.kmp.data.local.DataBaseFactory
import com.ee.kmp.data.remote.MainRepositoryImp
import com.ee.kmp.domine.MainRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformDataModule: Module

val dataModule = module {
    single {
        HttpClient {
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }

    singleOf(::MainRepositoryImp) { bind<MainRepository>() }

    factory {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single<BreedsDataBase> { DataBaseFactory(get()).invoke() }
}
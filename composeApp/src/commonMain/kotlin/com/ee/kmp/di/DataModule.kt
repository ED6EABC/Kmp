package com.ee.kmp.di

import com.breeds.BreedsDataBase
import com.ee.kmp.data.local.DataBaseFactory
import com.ee.kmp.data.remote.MainRepositoryImp
import com.ee.kmp.domine.MainRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformDataModule: Module

val dataModule = module {
    singleOf(::MainRepositoryImp) { bind<MainRepository>() }
    single<BreedsDataBase> { DataBaseFactory(get()).invoke() }
}
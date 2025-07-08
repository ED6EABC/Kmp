package com.ee.kmp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)

        modules(
            platformDataModule,

            dataModule,
            domineModule,
            presentationModule
        )
    }
}
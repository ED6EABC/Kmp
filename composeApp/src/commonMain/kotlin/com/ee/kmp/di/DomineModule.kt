package com.ee.kmp.di

import com.ee.kmp.domine.useCases.GetBreedsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domineModule = module {

    factoryOf(::GetBreedsUseCase)

}
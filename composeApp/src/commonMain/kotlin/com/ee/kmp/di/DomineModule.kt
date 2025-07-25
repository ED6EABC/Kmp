package com.ee.kmp.di

import com.ee.kmp.domine.useCases.CreateUserUseCase
import com.ee.kmp.domine.useCases.FindFavoriteUseCase
import com.ee.kmp.domine.useCases.GetBreedsUseCase
import com.ee.kmp.domine.useCases.GetFavoritesUseCase
import com.ee.kmp.domine.useCases.LoginUseCase
import com.ee.kmp.domine.useCases.RemoveFavoriteUseCase
import com.ee.kmp.domine.useCases.SaveFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domineModule = module {
    factoryOf(::GetBreedsUseCase)
    factoryOf(::SaveFavoriteUseCase)
    factoryOf(::GetFavoritesUseCase)
    factoryOf(::RemoveFavoriteUseCase)
    factoryOf(::FindFavoriteUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::CreateUserUseCase)
}
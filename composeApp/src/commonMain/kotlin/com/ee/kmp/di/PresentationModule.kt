package com.ee.kmp.di

import com.ee.kmp.ui.flows.login.LoginViewModel
import com.ee.kmp.ui.flows.breedList.BreedViewModel
import com.ee.kmp.ui.flows.favorites.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::BreedViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::FavoritesViewModel)
}
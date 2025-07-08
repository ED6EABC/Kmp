package com.ee.kmp.di

import com.ee.kmp.ui.flows.login.LoginViewModel
import com.ee.kmp.ui.flows.breedList.BreedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    //viewModelOf(::BreedViewModel)
    viewModel { BreedViewModel(get()) }
    viewModelOf(::LoginViewModel)
}
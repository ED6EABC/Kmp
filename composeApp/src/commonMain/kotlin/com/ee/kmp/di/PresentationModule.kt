package com.ee.kmp.di

import com.ee.kmp.ui.viewModels.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
}
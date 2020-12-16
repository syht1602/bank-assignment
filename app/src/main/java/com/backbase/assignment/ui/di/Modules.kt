package com.backbase.assignment.ui.di

import com.backbase.assignment.ui.viewmodels.MainViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
val gsonModule = module {
    fun provideGson(): Gson {
        return Gson().newBuilder().create()
    }
    single { provideGson() }
}

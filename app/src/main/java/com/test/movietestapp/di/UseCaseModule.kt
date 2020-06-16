package com.test.movietestapp.di

import com.test.movietestapp.domain.usecase.GetMoviesUseCase
import org.koin.android.module.AndroidModule

class UseCaseModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { GetMoviesUseCase(get()) }
    }
}
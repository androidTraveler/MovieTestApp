package com.test.movietestapp.di

import com.test.movietestapp.domain.usecase.TestUseCase
import org.koin.android.module.AndroidModule

class UseCaseModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { TestUseCase(get()) }
    }
}
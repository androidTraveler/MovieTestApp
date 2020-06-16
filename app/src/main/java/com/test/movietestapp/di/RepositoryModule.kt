package com.test.movietestapp.di

import com.test.movietestapp.data.repository.MainRepository
import com.test.movietestapp.data.repository.MainRepositoryImpl
import org.koin.android.module.AndroidModule

class RepositoryModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { MainRepositoryImpl(get(), get()) } bind (MainRepository::class)
    }
}
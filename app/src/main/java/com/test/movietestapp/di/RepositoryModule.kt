package com.test.movietestapp.di

import org.koin.android.module.AndroidModule
import com.test.movietestapp.data.repository.MainRepository
import com.test.movietestapp.data.repository.MainRepositoryImpl

class RepositoryModule: AndroidModule() {

    override fun context() = applicationContext {
        provide { MainRepositoryImpl() } bind (MainRepository::class)
    }
}
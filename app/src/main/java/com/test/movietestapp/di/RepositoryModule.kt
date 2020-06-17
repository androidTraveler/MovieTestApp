package com.test.movietestapp.di

import com.test.movietestapp.data.repository.local.LocalDataRepository
import com.test.movietestapp.data.repository.local.LocalDataRepositoryImpl
import com.test.movietestapp.data.repository.remote.MainRepository
import com.test.movietestapp.data.repository.remote.MainRepositoryImpl
import org.koin.android.module.AndroidModule

class RepositoryModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { MainRepositoryImpl(get(), get()) } bind (MainRepository::class)
        provide { LocalDataRepositoryImpl(get()) } bind (LocalDataRepository::class)
    }
}
package com.test.movietestapp.di

import com.test.movietestapp.data.model.transformer.MoviesTransformer
import org.koin.android.module.AndroidModule

class TransformerModule : AndroidModule() {

    override fun context() = applicationContext {
        provide { MoviesTransformer(get()) }
    }
}
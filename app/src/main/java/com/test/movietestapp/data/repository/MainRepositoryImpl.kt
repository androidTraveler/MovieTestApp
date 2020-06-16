package com.test.movietestapp.data.repository

import com.test.movietestapp.presentation.base.model.BaseModel
import io.reactivex.Single

class MainRepositoryImpl : MainRepository {

    override fun getInfo(): Single<BaseModel> {
        return Single.just(BaseModel())
    }
}
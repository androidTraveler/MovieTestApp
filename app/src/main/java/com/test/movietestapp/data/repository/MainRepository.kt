package com.test.movietestapp.data.repository

import com.test.movietestapp.presentation.base.model.BaseModel
import io.reactivex.Single

interface MainRepository {

    fun getInfo(): Single<BaseModel>
}
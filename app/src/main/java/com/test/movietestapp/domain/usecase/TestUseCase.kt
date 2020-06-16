package com.test.movietestapp.domain.usecase

import com.test.movietestapp.data.repository.MainRepository
import com.test.movietestapp.domain.base.UseCase
import com.test.movietestapp.presentation.base.model.BaseModel
import io.reactivex.Single

class TestUseCase(private val mainRepository: MainRepository) : UseCase.RxSingle<BaseModel>() {

    override fun build(): Single<BaseModel> = mainRepository.getInfo()

}
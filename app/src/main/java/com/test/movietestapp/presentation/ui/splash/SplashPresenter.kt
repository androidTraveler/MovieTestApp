package com.test.movietestapp.presentation.ui.splash

import com.test.movietestapp.data.model.response.GenresResponse
import com.test.movietestapp.data.repository.local.LocalDataRepository
import com.test.movietestapp.domain.usecase.GetGenresUseCase
import com.test.movietestapp.presentation.base.view.BasePresenter

class SplashPresenter(
    private val getGenresUseCase: GetGenresUseCase,
    private val localDataRepository: LocalDataRepository
) : BasePresenter<SplashContract.View>(), SplashContract.SplashPresenter {

    override fun startSplashTask(networkConnected: Boolean) {
        if (networkConnected) {
            getGenresUseCase.execute(
                onSuccess = { response ->
                    saveGenresLocally(response)
                    view?.startMainActivity()
                },
                onError = {
                    it.printStackTrace()
                    view?.startMainActivity()
                }
            )
        } else {
            view?.showNetworkPopupWithRetry()
        }
    }

    private fun saveGenresLocally(response: Pair<GenresResponse, GenresResponse>) {
        val map: MutableMap<Int, String> = mutableMapOf()
        response.first.genres?.forEach { map[it!!.id!!] = it.name!! }
        response.second.genres?.forEach { map[it!!.id!!] = it.name!! }
        localDataRepository.saveLocallyGenres(map)
    }


}
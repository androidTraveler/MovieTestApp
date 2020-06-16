package com.test.movietestapp.presentation.base.view

interface BaseView {

    val layoutResource: Int

    fun isNetworkConnected(): Boolean

    fun showNetworkPopup()
}
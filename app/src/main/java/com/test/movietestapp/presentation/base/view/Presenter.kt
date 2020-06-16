package com.test.movietestapp.presentation.base.view

interface Presenter<V> {

    fun attachView(view: V)

    fun detachView()

    var view : V?

}
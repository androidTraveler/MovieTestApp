package com.test.movietestapp.presentation.base.view

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> : Presenter<V> {

    private var baseView: WeakReference<V>? = null

    override var view: V? = null
        get() = baseView?.get()

    private val compositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        baseView = WeakReference(view)
    }

    override fun detachView() {
        compositeDisposable.clear()
        baseView?.clear()
        baseView = null
    }
}
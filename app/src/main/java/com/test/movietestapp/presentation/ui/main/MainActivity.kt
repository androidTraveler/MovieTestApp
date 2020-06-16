package com.test.movietestapp.presentation.ui.main

import android.os.Bundle
import com.test.movietestapp.R
import com.test.movietestapp.di.ActivityModule
import com.test.movietestapp.presentation.base.view.BaseActivity
import com.test.movietestapp.presentation.base.view.toast
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainContract.View, MainContract.MainPresenter>(),
    MainContract.View {

    override val contextName = ActivityModule.CTX_MAIN_ACTIVITY

    override val presenter by inject<MainContract.MainPresenter>()

    override val layoutResource: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.getInfo()
    }

    override fun showId(mId: Long) {
        toast(mId.toString())
    }
}
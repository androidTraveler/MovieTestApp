package com.test.movietestapp.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.test.movietestapp.R
import com.test.movietestapp.di.ActivityModule
import com.test.movietestapp.presentation.base.view.BaseActivity
import com.test.movietestapp.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<SplashContract.View, SplashContract.SplashPresenter>(),
    SplashContract.View {

    override val contextName = ActivityModule.CTX_SPLASH_ACTIVITY

    override val presenter by inject<SplashContract.SplashPresenter>()

    override val layoutResource: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.startSplashTask()
    }

    override fun startMainActivity() {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            iv_logo,
            getString(R.string.transition_name_logo)
        )
        startActivity(Intent(this, MainActivity::class.java), options.toBundle())
    }
}
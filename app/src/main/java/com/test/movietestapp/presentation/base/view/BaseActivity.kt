package com.test.movietestapp.presentation.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import org.koin.android.contextaware.ContextAwareActivity

abstract class BaseActivity<V : BaseView, out P : Presenter<V>> : ContextAwareActivity(), BaseView {

    private var rootView: android.view.View? = null

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflatedView = LayoutInflater.from(this).inflate(layoutResource, null)
        if (inflatedView is CoordinatorLayout) {
            rootView = inflatedView
        } else {
            rootView = CoordinatorLayout(this)
            (rootView as CoordinatorLayout).addView(
                inflatedView,
                CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.MATCH_PARENT
                )
            )
        }

        setContentView(rootView)

        presenter.attachView(this as V)
    }

    public override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}

package com.test.movietestapp.presentation.base.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.test.movietestapp.R
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

    override fun isNetworkConnected(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    override fun showNetworkPopup() {
        val alertDialogBuilder = AlertDialog.Builder(this, R.style.AlertDialogStyle)
        alertDialogBuilder.setTitle(getString(R.string.alert_no_internet_title))
        alertDialogBuilder.setMessage(getString(R.string.alert_no_internet_message))
        alertDialogBuilder.setPositiveButton(getString(R.string.alert_ok)) { dialog, _ -> dialog.dismiss() }
        alertDialogBuilder.show()
    }
}

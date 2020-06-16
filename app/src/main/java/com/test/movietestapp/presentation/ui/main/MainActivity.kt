package com.test.movietestapp.presentation.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.movietestapp.R
import com.test.movietestapp.di.ActivityModule
import com.test.movietestapp.presentation.base.view.BaseActivity
import com.test.movietestapp.presentation.base.view.toast
import com.test.movietestapp.presentation.model.MovieModel
import com.test.movietestapp.presentation.ui.main.adapter.MovieAdapter
import com.test.movietestapp.presentation.ui.main.adapter.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainContract.View, MainContract.MainPresenter>(),
    MainContract.View, MovieAdapter.OnItemClickListener {

    private val postAdapter = MovieAdapter(this, this)
    var isLastPage: Boolean = false

    override val contextName = ActivityModule.CTX_MAIN_ACTIVITY

    override val presenter by inject<MainContract.MainPresenter>()

    override val layoutResource: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        loadDataFromServer()
    }

    private fun initUI() {
        setUpRecycler()
        setUpRefresher()
//        iv_search.setOnClickListener { view ->  } TODO implement click
    }

    private fun loadDataFromServer() {
        presenter.loadDataFromServer(isNetworkConnected())
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(this)
        rv_movies.layoutManager = layoutManager
        rv_movies.adapter = postAdapter
        rv_movies.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return sr_refresh.isRefreshing
            }

            override fun loadMoreItems() {
                presenter.loadMoreData(isNetworkConnected())
            }
        })
    }

    private fun setUpRefresher() {
        sr_refresh.setOnRefreshListener { loadDataFromServer() }
    }

    override fun showRefresh() {
        sr_refresh.isRefreshing = true
    }

    override fun hideRefresh() {
        sr_refresh.isRefreshing = false
    }

    override fun fillData(moviesList: MutableList<MovieModel>) {
        postAdapter.replaceData(moviesList)
    }

    override fun addData(moviesList: MutableList<MovieModel>) {
        postAdapter.addData(moviesList)
    }

    override fun errorGetPosts() {
        toast(getString(R.string.error_no_data))
    }

    override fun showNoMoreMovies() {
        toast(getString(R.string.error_no_more_movies))
    }

    override fun changeLastPage(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }

    override fun onListItemClick(item: MovieModel) {
//        TODO implement click
    }
}
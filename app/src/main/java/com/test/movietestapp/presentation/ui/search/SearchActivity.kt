package com.test.movietestapp.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.movietestapp.R
import com.test.movietestapp.data.constants.Constants
import com.test.movietestapp.di.ActivityModule
import com.test.movietestapp.presentation.base.view.BaseActivity
import com.test.movietestapp.presentation.base.view.toast
import com.test.movietestapp.presentation.model.MovieModel
import com.test.movietestapp.presentation.ui.details.DetailsActivity
import com.test.movietestapp.presentation.ui.main.adapter.MovieAdapter
import com.test.movietestapp.presentation.ui.main.adapter.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity<SearchContract.View, SearchContract.SearchPresenter>(),
    SearchContract.View, MovieAdapter.OnItemClickListener {

    private val movieAdapter = MovieAdapter(this, this)
    var isLastPage: Boolean = false

    override val contextName = ActivityModule.CTX_SEARCH_ACTIVITY

    override val presenter by inject<SearchContract.SearchPresenter>()

    override val layoutResource: Int = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecycler()
        setUpRefresher()
        setUpSearchView()
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(this)
        rv_movies.layoutManager = layoutManager
        rv_movies.adapter = movieAdapter
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
        sr_refresh.setOnRefreshListener { searchMovies() }
    }

    private fun setUpSearchView() {
        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            OnQueryTextListener {

            override fun onQueryTextChange(s: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                searchMovies()
                return true
            }
        })
    }

    private fun searchMovies() {
        presenter.searchMovies(isNetworkConnected(), sv_search.query.toString().trim())
    }

    override fun showRefresh() {
        sr_refresh.isRefreshing = true
    }

    override fun hideRefresh() {
        sr_refresh.isRefreshing = false
    }

    override fun fillData(moviesList: MutableList<MovieModel>) {
        tv_message.text = ""
        movieAdapter.replaceData(moviesList)
    }

    override fun addData(moviesList: MutableList<MovieModel>) {
        movieAdapter.addData(moviesList)
    }

    override fun errorGetMovies() {
        toast(getString(R.string.error_no_data))
    }

    override fun errorNoMoviesByQuery() {
        val message = getString(R.string.no_movie_by_query)
        tv_message.text = message
        toast(message)
    }

    override fun showNoMoreMovies() {
        toast(getString(R.string.error_no_more_movies))
    }

    override fun showEmptyQuery() {
        val message = getString(R.string.provide_query)
        tv_message.text = message
        toast(message)
    }

    override fun changeLastPage(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }

    override fun onListItemClick(item: MovieModel, view: View) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(Constants.EXTRA_MOVIE_ITEM, item)
        val p1 = Pair(view.tv_title as View?, getString(R.string.transition_name_title))
        val p2 = Pair(iv_logo as View?, getString(R.string.transition_name_logo))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2)
        startActivity(intent, options.toBundle())
    }
}
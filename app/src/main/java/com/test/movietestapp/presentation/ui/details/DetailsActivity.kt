package com.test.movietestapp.presentation.ui.details

import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.test.movietestapp.R
import com.test.movietestapp.data.constants.Constants
import com.test.movietestapp.data.constants.RestConst
import com.test.movietestapp.di.ActivityModule
import com.test.movietestapp.presentation.base.view.BaseActivity
import com.test.movietestapp.presentation.model.MovieModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_movie.tv_title
import org.koin.android.ext.android.inject

class DetailsActivity : BaseActivity<DetailsContract.View, DetailsContract.DetailsPresenter>(),
    DetailsContract.View {

    override val contextName = ActivityModule.CTX_DETAILS_ACTIVITY

    override val presenter by inject<DetailsContract.DetailsPresenter>()

    override val layoutResource: Int = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.checkBundle(intent?.getParcelableExtra(Constants.EXTRA_MOVIE_ITEM))
    }

    override fun fillData(model: MovieModel?) {
        tv_title.text = model?.title
        tv_overview.text = model?.overview
        Glide.with(this)
            .load(Uri.parse(RestConst.IMAGE_LOAD_URL + model?.posterPath))
            .thumbnail(0.1f)
            .centerCrop()
            .into(iv_poster)
        tv_original_title.text = model?.originalTitle
        tv_original_language.text = model?.originalLanguage
        tv_adult.text = if (model?.adult!!) getString(R.string.yes) else getString(R.string.no)
    }
}
package com.test.movietestapp.presentation.ui.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.movietestapp.R
import com.test.movietestapp.data.constants.RestConst
import com.test.movietestapp.presentation.model.MovieModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.concurrent.TimeUnit

class MovieAdapter(private val context: Context, private val listener: OnItemClickListener?) :
    RecyclerView.Adapter<MovieAdapter.PostViewHolder>() {

    private val items: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position], listener)
        holder.tvTitle.text = items[position].title
        holder.tvOverview.text = items[position].overview
        holder.tvGenre.text =
            if (items[position].genres.isNullOrEmpty()) context.getString(R.string.genre_not_specified) else items[position].genres.toString()
        holder.tvReleaseDate.text =
            context.getString(R.string.release_date, items[position].releaseDate)
        holder.tvPopularity.text =
            context.getString(
                R.string.popularity,
                String.format("%.2f", items[position].popularity)
            )
        Glide.with(context)
            .load(Uri.parse(RestConst.IMAGE_LOAD_URL + items[position].posterPath))
            .placeholder(R.drawable.ic_no_icon)
            .into(holder.ivThumbnail)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun replaceData(posts: List<MovieModel>) {
        items.clear()
        items.addAll(posts)
        notifyDataSetChanged()
    }

    fun addData(posts: List<MovieModel>) {
        val size = this.items.size
        this.items.addAll(posts)
        val sizeNew = this.items.size
        notifyItemRangeChanged(size, sizeNew)
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.tv_title
        val tvOverview: TextView = view.tv_overview
        val tvGenre: TextView = view.tv_genre
        val ivThumbnail: AppCompatImageView = view.iv_thumbnail
        val tvReleaseDate: TextView = view.tv_release_date
        val tvPopularity: TextView = view.tv_popularity
        private var allowClick = true //for preventing fast multiple click on item

        fun bind(item: MovieModel, listener: OnItemClickListener?) {
            itemView.setOnClickListener {
                if (allowClick) {
                    listener?.onListItemClick(item, itemView)
                    allowClick = false
                    Observable.timer(300, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { allowClick = true }
                        .subscribe()
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onListItemClick(item: MovieModel, view: View)
    }

}
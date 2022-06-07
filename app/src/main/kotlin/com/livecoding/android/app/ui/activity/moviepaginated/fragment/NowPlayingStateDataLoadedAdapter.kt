package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.NowPlayingItemStateDataLoadedBinding
import com.livecoding.android.app.ui.ext.load
import com.livecoding.android.app.ui.model.Movie

class NowPlayingStateDataLoadedAdapter :
    PagingDataAdapter<Movie, NowPlayingStateDataLoadedAdapter.MovieDataStateViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NowPlayingItemStateDataLoadedBinding.inflate(layoutInflater, parent, false)

        return MovieDataStateViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: MovieDataStateViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class MovieDataStateViewHolder(
        private val binding: NowPlayingItemStateDataLoadedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var movie: Movie? = null

        fun bindTo(movie: Movie?) {
            this.movie = movie

            movie?.let {
                binding.tvTitle.text = it.title
                binding.tvOverview.text = it.overview
                binding.ivPoster.load(it.poster, R.drawable.ic_placeholder_movie)
            }
        }

        companion object {
            fun create(binding: NowPlayingItemStateDataLoadedBinding): MovieDataStateViewHolder {
                return MovieDataStateViewHolder(binding)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldMovie: Movie,
                newMovie: Movie
            ): Boolean =
                oldMovie.id == newMovie.id

            override fun areContentsTheSame(
                oldMovie: Movie,
                newMovie: Movie
            ): Boolean =
                oldMovie == newMovie
        }
    }
}
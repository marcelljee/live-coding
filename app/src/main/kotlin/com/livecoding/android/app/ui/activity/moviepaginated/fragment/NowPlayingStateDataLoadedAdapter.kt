package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.NowPlayingItemStateDataLoadedBinding
import com.livecoding.android.app.ui.ext.load
import com.livecoding.android.app.ui.model.Movie

class NowPlayingStateDataLoadedAdapter :
    PagingDataAdapter<Movie, NowPlayingStateDataLoadedAdapter.MovieDataStateViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_playing_item_state_data_loaded, parent, false)

        return MovieDataStateViewHolder.create(view)
    }

    override fun onBindViewHolder(holder: MovieDataStateViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class MovieDataStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = NowPlayingItemStateDataLoadedBinding.bind(itemView)

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
            fun create(itemView: View): MovieDataStateViewHolder {
                return MovieDataStateViewHolder(itemView)
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
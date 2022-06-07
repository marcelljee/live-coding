package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.livecoding.android.app.databinding.ItemMovieLoadStateBinding

class MovieLoadStateAdapter : LoadStateAdapter<MovieLoadStateAdapter.MovieLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieLoadStateBinding.inflate(layoutInflater, parent, false)

        return MovieLoadStateViewHolder.create(binding)
    }

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        /* bind nothing */
    }

    class MovieLoadStateViewHolder(
        private val binding: ItemMovieLoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(binding: ItemMovieLoadStateBinding): MovieLoadStateViewHolder {
                return MovieLoadStateViewHolder(binding)
            }
        }
    }
}
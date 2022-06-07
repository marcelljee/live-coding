package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.livecoding.android.app.R

class NowPlayingStateLoadMoreAdapter :
    LoadStateAdapter<NowPlayingStateLoadMoreAdapter.MovieLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_playing_item_state_load_more, parent, false)

        return MovieLoadStateViewHolder.create(view)
    }

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        /* bind nothing */
    }

    class MovieLoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun create(itemView: View): MovieLoadStateViewHolder {
                return MovieLoadStateViewHolder(itemView)
            }
        }
    }
}
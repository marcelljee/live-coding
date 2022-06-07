package com.livecoding.android.app.ui.activity.moviepaginated.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.livecoding.android.app.data.MovieRepository
import com.livecoding.android.app.ui.model.Movie
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviePaginatedViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    val nowPlayingMovies = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = movieRepository.getNowPlayingRemoteMediator()
    ) {
        movieRepository.getNowPlayingPagingSource()
    }.flow
        .map { pagingData ->
            pagingData.map {
                Movie(
                    it.id,
                    it.title,
                    it.overview,
                    it.poster
                )
            }
        }
        .cachedIn(viewModelScope)
}
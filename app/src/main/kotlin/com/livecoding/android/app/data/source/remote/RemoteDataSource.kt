package com.livecoding.android.app.data.source.remote

import com.livecoding.android.app.data.model.Movie
import com.livecoding.android.app.data.model.NowPlaying
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSource @Inject constructor(
    private val movieService: MovieService,
    private val apiKey: String,
    private val apiLang: String,
    private val posterBaseUrl: String
) {
    suspend fun fetchNowPlaying(page: Int): NowPlaying {
        return movieService.fetchNowPlaying(apiKey, apiLang, page).let {
            NowPlaying(it.page, it.results?.map { movieResponse ->
                Movie(
                    movieResponse.id,
                    movieResponse.title,
                    movieResponse.overview,
                    posterBaseUrl + movieResponse.poster
                )
            }, it.totalPages)
        }
    }
}
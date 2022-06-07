package com.livecoding.android.app.data.source.remote

import com.livecoding.android.app.data.model.Movie
import com.livecoding.android.app.data.model.NowPlaying
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import com.livecoding.android.app.ui.di.qualifier.ApiKeyQualifier
import com.livecoding.android.app.ui.di.qualifier.ApiLangQualifier
import com.livecoding.android.app.ui.di.qualifier.MoviePosterBaseUrlQualifier
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val liveCodingService: MovieService,
    @ApiKeyQualifier private val apiKey: String,
    @ApiLangQualifier private val apiLang: String,
    @MoviePosterBaseUrlQualifier private val posterBaseUrl: String
) {
    suspend fun fetchNowPlaying(page: Int): NowPlaying {
        return liveCodingService.fetchNowPlaying(apiKey, apiLang, page).let {
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
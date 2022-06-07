package com.livecoding.android.app.data.source.remote.api.service

import com.livecoding.android.app.data.source.remote.api.response.NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/now_playing")
    suspend fun fetchNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int = 1
    ): NowPlayingResponse
}
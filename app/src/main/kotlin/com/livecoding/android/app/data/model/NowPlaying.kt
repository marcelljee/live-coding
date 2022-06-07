package com.livecoding.android.app.data.model

data class NowPlaying(
    val page: Int?,
    val results: List<Movie>?,
    val totalPages: Int?
)
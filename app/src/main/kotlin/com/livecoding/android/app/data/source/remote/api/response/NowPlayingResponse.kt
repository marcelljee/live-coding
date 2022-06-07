package com.livecoding.android.app.data.source.remote.api.response

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieResponse>?,
    @SerializedName("total_pages") val totalPages: Int?
)
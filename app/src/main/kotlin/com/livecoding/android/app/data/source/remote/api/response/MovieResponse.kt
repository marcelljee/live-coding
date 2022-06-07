package com.livecoding.android.app.data.source.remote.api.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val poster: String?
)
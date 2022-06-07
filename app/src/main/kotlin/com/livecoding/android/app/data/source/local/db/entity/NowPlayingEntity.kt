package com.livecoding.android.app.data.source.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing")
data class NowPlayingEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "poster") val poster: String?
)
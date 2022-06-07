package com.livecoding.android.app.data.source.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.livecoding.android.app.data.source.local.db.entity.NowPlayingEntity

@Dao
interface NowPlayingDao {
    @Query("SELECT * FROM now_playing")
    fun nowPlayingPagingSource(): PagingSource<Int, NowPlayingEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(nowPlayingEntities: List<NowPlayingEntity>)

    @Query("DELETE FROM now_playing")
    suspend fun clearAll()
}
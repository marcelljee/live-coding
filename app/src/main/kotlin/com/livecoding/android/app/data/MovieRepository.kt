package com.livecoding.android.app.data

import androidx.paging.PagingSource
import com.livecoding.android.app.data.source.local.LocalDataSource
import com.livecoding.android.app.data.source.local.db.entity.NowPlayingEntity
import com.livecoding.android.app.data.source.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val nowPlayingRemoteMediator: NowPlayingRemoteMediator
) {
    fun getNowPlayingRemoteMediator(): NowPlayingRemoteMediator {
        return nowPlayingRemoteMediator
    }

    fun getNowPlayingPagingSource(): PagingSource<Int, NowPlayingEntity> {
        return localDataSource.nowPlayingPagingSource()
    }
}
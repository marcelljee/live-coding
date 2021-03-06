package com.livecoding.android.app.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.livecoding.android.app.data.source.local.LocalDataSource
import com.livecoding.android.app.data.source.local.db.AppDatabase
import com.livecoding.android.app.data.source.local.db.entity.NowPlayingEntity
import com.livecoding.android.app.data.source.remote.RemoteDataSource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NowPlayingRemoteMediator @Inject constructor(
    private val cacheTimeoutMillis: Long,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, NowPlayingEntity>() {

    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - localDataSource.getNowPlayingDbLastUpdated() <= cacheTimeoutMillis) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingEntity>
    ): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> this.currentPage = 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> this.currentPage++
            }

            val response = remoteDataSource.fetchNowPlaying(this.currentPage)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localDataSource.deleteAllNowPlayings()
                }

                this.currentPage = response.page ?: this.currentPage

                localDataSource.storeNowPlayings(response.results)
                localDataSource.setNowPlayingDbLastUpdated(System.currentTimeMillis())
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}

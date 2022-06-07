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
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NowPlayingRemoteMediator @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, NowPlayingEntity>() {

    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - localDataSource.getLastUpdated() >= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NowPlayingEntity>
    ): MediatorResult {
        return try {
            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = remoteDataSource.fetchNowPlaying(currentPage + 1)

            if (response.page != null) {
                appDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.deleteAllNowPlayings()
                    }

                    this.currentPage = response.page

                    // Insert new users into database, which invalidates the
                    // current PagingData, allowing Paging to present the updates
                    // in the DB.
                    localDataSource.storeNowPlayings(response.results)
                    localDataSource.setLastUpdated(System.currentTimeMillis())
                }

                MediatorResult.Success(
                    endOfPaginationReached = this.currentPage == response.totalPages
                )
            } else {
                MediatorResult.Error(NullPointerException("page is null"))
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}

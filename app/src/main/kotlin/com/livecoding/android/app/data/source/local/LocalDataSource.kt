package com.livecoding.android.app.data.source.local

import androidx.paging.PagingSource
import com.livecoding.android.app.data.model.Movie
import com.livecoding.android.app.data.model.Shipment
import com.livecoding.android.app.data.source.local.db.dao.NowPlayingDao
import com.livecoding.android.app.data.source.local.db.entity.NowPlayingEntity
import com.livecoding.android.app.data.source.local.json.ShipmentJsonFileManager
import com.livecoding.android.app.data.source.local.sp.PreferencesManager
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class LocalDataSource @Inject constructor(
    private val nowPlayingDao: NowPlayingDao,
    private val preferencesManager: PreferencesManager,
    private val shipmentJsonFileManager: ShipmentJsonFileManager
) {
    fun nowPlayingPagingSource(): PagingSource<Int, NowPlayingEntity> {
        return nowPlayingDao.nowPlayingPagingSource()
    }

    suspend fun storeNowPlayings(movies: List<Movie>?) {
        if (movies != null) {
            nowPlayingDao.insertAll(movies.mapNotNull { movie ->
                if (movie.id != null) {
                    NowPlayingEntity(
                        movie.id,
                        movie.title,
                        movie.overview,
                        movie.poster
                    )
                } else {
                    null
                }
            })
        }
    }

    suspend fun deleteAllNowPlayings() {
        nowPlayingDao.clearAll()
    }

    fun getNowPlayingDbLastUpdated(): Long {
        return preferencesManager.getNowPlayingDbLastUpdated()
    }

    fun setNowPlayingDbLastUpdated(timeMillis: Long) {
        preferencesManager.setNowPlayingDbLastUpdated(timeMillis)
    }

    fun getShipments(): List<Shipment> {
        return shipmentJsonFileManager.getShipments().map {
            Shipment(
                it.origin,
                it.destination,
                it.price,
                it.date
            )
        }
    }
}
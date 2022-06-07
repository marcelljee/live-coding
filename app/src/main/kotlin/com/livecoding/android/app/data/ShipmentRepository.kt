package com.livecoding.android.app.data

import com.livecoding.android.app.data.model.Shipment
import com.livecoding.android.app.data.source.local.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShipmentRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    fun getShipments(): List<Shipment> {
        return localDataSource.getShipments()
    }
}
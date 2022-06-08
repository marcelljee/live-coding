package com.livecoding.android.app.data

import com.livecoding.android.app.data.model.Shipment
import com.livecoding.android.app.data.source.local.LocalDataSource
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class ShipmentRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    fun getShipments(): List<Shipment> {
        return localDataSource.getShipments()
    }
}
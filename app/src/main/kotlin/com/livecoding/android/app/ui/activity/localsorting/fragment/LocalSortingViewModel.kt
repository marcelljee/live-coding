package com.livecoding.android.app.ui.activity.localsorting.fragment

import androidx.lifecycle.ViewModel
import com.livecoding.android.app.data.ShipmentRepository
import com.livecoding.android.app.ui.model.Shipment
import java.time.Instant
import javax.inject.Inject

class LocalSortingViewModel @Inject constructor(
    private val shipmentRepository: ShipmentRepository
) : ViewModel() {
    fun getShipments(): List<Shipment> {
        return shipmentRepository.getShipments().map {
            Shipment(
                it.origin,
                it.destination,
                it.price,
                Instant.parse(it.date)
            )
        }
    }
}
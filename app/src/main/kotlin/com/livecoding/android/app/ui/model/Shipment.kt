package com.livecoding.android.app.ui.model

import java.time.Instant

data class Shipment(
    val origin: String?,
    val destination: String?,
    val price: Double?,
    val date: Instant?
)
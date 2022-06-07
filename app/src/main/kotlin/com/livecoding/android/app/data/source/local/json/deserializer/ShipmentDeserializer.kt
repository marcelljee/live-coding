package com.livecoding.android.app.data.source.local.json.deserializer

import com.google.gson.annotations.SerializedName

data class ShipmentDeserializer(
    @SerializedName("origin") val origin: String?,
    @SerializedName("destination") val destination: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("loadingDate") val date: String?
)
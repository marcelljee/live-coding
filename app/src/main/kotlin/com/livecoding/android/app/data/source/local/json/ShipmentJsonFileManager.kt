package com.livecoding.android.app.data.source.local.json

import com.google.gson.reflect.TypeToken
import com.livecoding.android.app.data.source.local.assets.AssetsManager
import com.livecoding.android.app.data.source.local.json.deserializer.ShipmentDeserializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShipmentJsonFileManager @Inject constructor(
    private val assetsManager: AssetsManager,
    private val jsonParser: JsonParser
) {
    fun getShipments(): List<ShipmentDeserializer> {
        val jsonContent = assetsManager.getFileContentFromAssets("shipments.json")
        val listShipmentType = object : TypeToken<List<ShipmentDeserializer>>() {}.type

        return jsonParser.parseJson(jsonContent, listShipmentType)
    }
}
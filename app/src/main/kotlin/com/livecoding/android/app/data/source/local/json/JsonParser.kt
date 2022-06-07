package com.livecoding.android.app.data.source.local.json

import com.google.gson.Gson
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonParser @Inject constructor(
    private val gson: Gson
) {
    fun <T> parseJson(jsonContent: String?, type: Type): T {
        return gson.fromJson(jsonContent, type)
    }
}
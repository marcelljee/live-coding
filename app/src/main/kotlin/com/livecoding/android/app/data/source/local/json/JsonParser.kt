package com.livecoding.android.app.data.source.local.json

import com.google.gson.Gson
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import java.lang.reflect.Type
import javax.inject.Inject

@ApplicationScope
class JsonParser @Inject constructor(
    private val gson: Gson
) {
    fun <T> parseJson(jsonContent: String?, type: Type): T {
        return gson.fromJson(jsonContent, type)
    }
}
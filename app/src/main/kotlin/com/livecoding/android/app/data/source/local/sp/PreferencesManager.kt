package com.livecoding.android.app.data.source.local.sp

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_LAST_UPDATED =
    "com.livecoding.android.app.data.source.local.sp.LAST_UPDATED"

@Singleton
class PreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getLastUpdated(): Long {
        return sharedPreferences.getLong(KEY_LAST_UPDATED, 0)
    }

    fun setLastUpdated(timestamp: Long) {
        sharedPreferences.edit().putLong(KEY_LAST_UPDATED, timestamp).apply()
    }
}
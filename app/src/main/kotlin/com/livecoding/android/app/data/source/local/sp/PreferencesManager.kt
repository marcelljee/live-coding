package com.livecoding.android.app.data.source.local.sp

import android.content.SharedPreferences
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import javax.inject.Inject

private const val KEY_NOW_PLAYING_LAST_UPDATED =
    "com.livecoding.android.app.data.source.local.sp.NOW_PLAYING_LAST_UPDATED"

@ApplicationScope
class PreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getNowPlayingDbLastUpdated(): Long {
        return sharedPreferences.getLong(KEY_NOW_PLAYING_LAST_UPDATED, 0)
    }

    fun setNowPlayingDbLastUpdated(timestamp: Long) {
        sharedPreferences.edit().putLong(KEY_NOW_PLAYING_LAST_UPDATED, timestamp).apply()
    }
}
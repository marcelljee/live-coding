package com.livecoding.android.app.ui.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.livecoding.android.app.ui.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(application: MainApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
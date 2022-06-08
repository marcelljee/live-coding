package com.livecoding.android.app.ui.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.livecoding.android.app.ui.MainApplication
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {
    @ApplicationScope
    @Provides
    fun provideSharedPreferences(application: MainApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}
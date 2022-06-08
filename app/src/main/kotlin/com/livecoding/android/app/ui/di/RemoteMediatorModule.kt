package com.livecoding.android.app.ui.di

import com.livecoding.android.app.ui.di.qualifier.CacheTimeoutQualifier
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteMediatorModule {

    @Singleton
    @Provides
    @CacheTimeoutQualifier
    fun provideCacheTimeout(): Long {
        return TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
    }

}
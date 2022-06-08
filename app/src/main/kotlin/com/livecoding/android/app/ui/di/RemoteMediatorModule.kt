package com.livecoding.android.app.ui.di

import com.livecoding.android.app.data.NowPlayingRemoteMediator
import com.livecoding.android.app.data.source.local.LocalDataSource
import com.livecoding.android.app.data.source.local.db.AppDatabase
import com.livecoding.android.app.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteMediatorModule {

    @Singleton
    @Provides
    fun provideNowPlayingRemoteMediator(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        appDatabase: AppDatabase
    ): NowPlayingRemoteMediator {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return NowPlayingRemoteMediator(
            cacheTimeout,
            localDataSource,
            remoteDataSource,
            appDatabase
        )
    }

}
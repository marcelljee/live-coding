package com.livecoding.android.app.ui.di

import com.livecoding.android.app.data.NowPlayingRemoteMediator
import com.livecoding.android.app.data.source.local.LocalDataSource
import com.livecoding.android.app.data.source.local.db.AppDatabase
import com.livecoding.android.app.data.source.remote.RemoteDataSource
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit

@Module
class RemoteMediatorModule {

    @ApplicationScope
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
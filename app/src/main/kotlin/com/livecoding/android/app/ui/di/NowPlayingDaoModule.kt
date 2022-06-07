package com.livecoding.android.app.ui.di

import com.livecoding.android.app.data.source.local.db.AppDatabase
import com.livecoding.android.app.data.source.local.db.dao.NowPlayingDao
import com.livecoding.android.app.ui.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NowPlayingDaoModule {

    @Singleton
    @Provides
    fun provideNowPlayingDao(appDatabase: AppDatabase): NowPlayingDao {
        return appDatabase.nowPlayingDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(application: MainApplication): AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }
}
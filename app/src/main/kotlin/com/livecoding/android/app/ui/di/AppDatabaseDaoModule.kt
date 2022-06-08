package com.livecoding.android.app.ui.di

import com.livecoding.android.app.data.source.local.db.AppDatabase
import com.livecoding.android.app.data.source.local.db.dao.NowPlayingDao
import com.livecoding.android.app.ui.MainApplication
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseDaoModule {

    @ApplicationScope
    @Provides
    fun provideNowPlayingDao(appDatabase: AppDatabase): NowPlayingDao {
        return appDatabase.nowPlayingDao()
    }

    @ApplicationScope
    @Provides
    fun provideAppDatabase(application: MainApplication): AppDatabase {
        return AppDatabase.getInstance(application.applicationContext)
    }
}
package com.livecoding.android.app.ui.di

import com.livecoding.android.app.data.source.remote.RemoteDataSource
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RemoteDataSourceModule {

    @ApplicationScope
    @Provides
    fun provideRemoteDataSourceModule(
        movieService: MovieService
    ): RemoteDataSource {
        val apiKey = "6a0ff284c12c6a9522dfe9623ed5c8c7"
        val lang = "en-US"
        val posterBaseUrl = "https://image.tmdb.org/t/p/w500"

        return RemoteDataSource(movieService, apiKey, lang, posterBaseUrl)
    }

}
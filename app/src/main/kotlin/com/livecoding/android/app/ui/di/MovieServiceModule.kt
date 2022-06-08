package com.livecoding.android.app.ui.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import com.livecoding.android.app.ui.di.qualifier.ApiBaseUrlQualifier
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MovieServiceModule {

    @ApplicationScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(
        @ApiBaseUrlQualifier url: String,
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @ApplicationScope
    @Provides
    @ApiBaseUrlQualifier
    fun provideApiBaseUrl(): String {
        return "https://api.themoviedb.org/"
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
}
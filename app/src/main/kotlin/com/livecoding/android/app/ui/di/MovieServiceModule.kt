package com.livecoding.android.app.ui.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import com.livecoding.android.app.ui.di.qualifier.ApiBaseUrlQualifier
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MovieServiceModule {

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
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

    @Singleton
    @Provides
    @ApiBaseUrlQualifier
    fun provideApiBaseUrl(): String {
        return "https://api.themoviedb.org/"
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
}
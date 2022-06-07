package com.livecoding.android.app.ui.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.livecoding.android.app.data.source.remote.api.service.MovieService
import com.livecoding.android.app.ui.di.qualifier.ApiBaseUrlQualifier
import com.livecoding.android.app.ui.di.qualifier.ApiKeyQualifier
import com.livecoding.android.app.ui.di.qualifier.ApiLangQualifier
import com.livecoding.android.app.ui.di.qualifier.MoviePosterBaseUrlQualifier
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
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Singleton
    @Provides
    @ApiBaseUrlQualifier
    fun provideApiBaseUrl(): String {
        return "https://api.themoviedb.org/"
    }

    @Singleton
    @Provides
    @ApiKeyQualifier
    fun provideApiKey(): String {
        return "6a0ff284c12c6a9522dfe9623ed5c8c7"
    }

    @Singleton
    @Provides
    @ApiLangQualifier
    fun provideApiLang(): String {
        return "en-US"
    }

    @Singleton
    @Provides
    @MoviePosterBaseUrlQualifier
    fun provideMoviePosterBaseUrl(): String {
        return "https://image.tmdb.org/t/p/w500"
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
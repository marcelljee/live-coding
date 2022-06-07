package com.livecoding.android.app.ui.di.fragment

import com.livecoding.android.app.ui.activity.moviepaginated.fragment.MoviePaginatedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviePaginatedFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMoviePaginatedFragment(): MoviePaginatedFragment
}
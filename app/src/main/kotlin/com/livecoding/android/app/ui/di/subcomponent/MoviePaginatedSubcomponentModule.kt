package com.livecoding.android.app.ui.di.subcomponent

import com.livecoding.android.app.ui.activity.moviepaginated.fragment.MoviePaginatedFragment
import com.livecoding.android.app.ui.di.scope.ActivityScope
import com.livecoding.android.app.ui.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviePaginatedSubcomponentModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeMoviePaginatedFragment(): MoviePaginatedFragment
}
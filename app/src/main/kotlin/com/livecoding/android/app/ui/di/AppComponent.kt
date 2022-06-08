package com.livecoding.android.app.ui.di

import com.livecoding.android.app.ui.MainApplication
import com.livecoding.android.app.ui.di.fragment.HomeFragmentModule
import com.livecoding.android.app.ui.di.fragment.LocalSortingFragmentModule
import com.livecoding.android.app.ui.di.fragment.MoviePaginatedFragmentModule
import com.livecoding.android.app.ui.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        NowPlayingDaoModule::class,
        MovieServiceModule::class,
        SharedPreferencesModule::class,
        HomeFragmentModule::class,
        LocalSortingFragmentModule::class,
        MoviePaginatedFragmentModule::class,
        RemoteMediatorModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}
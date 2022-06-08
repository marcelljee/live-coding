package com.livecoding.android.app.ui.di

import com.livecoding.android.app.ui.MainApplication
import com.livecoding.android.app.ui.di.scope.ApplicationScope
import com.livecoding.android.app.ui.di.subcomponent.HomeSubcomponentModule
import com.livecoding.android.app.ui.di.subcomponent.LocalSortingSubcomponentModule
import com.livecoding.android.app.ui.di.subcomponent.MoviePaginatedSubcomponentModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppDatabaseDaoModule::class,
        MovieServiceModule::class,
        SharedPreferencesModule::class,
        RemoteMediatorModule::class,
        GsonModule::class,
        RemoteDataSourceModule::class,
        HomeSubcomponentModule::class,
        LocalSortingSubcomponentModule::class,
        MoviePaginatedSubcomponentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}
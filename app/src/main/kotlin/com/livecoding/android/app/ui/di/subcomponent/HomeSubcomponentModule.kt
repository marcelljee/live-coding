package com.livecoding.android.app.ui.di.subcomponent

import com.livecoding.android.app.ui.activity.home.fragment.HomeFragment
import com.livecoding.android.app.ui.di.scope.ActivityScope
import com.livecoding.android.app.ui.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeSubcomponentModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeHomeFragmentModule(): HomeFragment
}
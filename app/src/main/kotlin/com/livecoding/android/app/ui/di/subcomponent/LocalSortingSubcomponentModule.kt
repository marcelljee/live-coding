package com.livecoding.android.app.ui.di.subcomponent

import com.livecoding.android.app.ui.activity.localsorting.fragment.LocalSortingFragment
import com.livecoding.android.app.ui.di.scope.ActivityScope
import com.livecoding.android.app.ui.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LocalSortingSubcomponentModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class
        ]
    )
    abstract fun contributeLocalSortingFragment(): LocalSortingFragment
}
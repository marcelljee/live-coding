package com.livecoding.android.app.ui.di.fragment

import com.livecoding.android.app.ui.activity.localsorting.fragment.LocalSortingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LocalSortingFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeLocalSortingFragment(): LocalSortingFragment
}
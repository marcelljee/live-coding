package com.livecoding.android.app.ui.di.fragment

import com.livecoding.android.app.ui.activity.home.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragmentModule(): HomeFragment
}
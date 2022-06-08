package com.livecoding.android.app.ui.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.livecoding.android.app.ui.activity.localsorting.fragment.LocalSortingViewModel
import com.livecoding.android.app.ui.activity.moviepaginated.fragment.MoviePaginatedViewModel
import com.livecoding.android.app.ui.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @ActivityScope
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(MoviePaginatedViewModel::class)
    internal abstract fun bindMoviePaginatedViewModel(moviePaginatedViewModel: MoviePaginatedViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKey(LocalSortingViewModel::class)
    internal abstract fun bindLocalSortingViewModel(localSortingViewModel: LocalSortingViewModel): ViewModel
}
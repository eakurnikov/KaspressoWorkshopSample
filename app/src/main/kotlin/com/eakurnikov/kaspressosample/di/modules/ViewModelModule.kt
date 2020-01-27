package com.eakurnikov.kaspressosample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eakurnikov.common.annotations.ViewModelKey
import com.eakurnikov.kaspressosample.viewmodel.posts.PostsViewModel
import com.eakurnikov.kaspressosample.viewmodel.base.ViewModelFactory
import com.eakurnikov.kaspressosample.viewmodel.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
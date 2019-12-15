package com.eakurnikov.kaspressosample.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eakurnikov.common.annotations.ViewModelKey
import com.eakurnikov.kaspressosample.viewmodel.posts.PostsViewModel
import com.eakurnikov.kaspressosample.viewmodel.base.ViewModelFactory
import com.eakurnikov.kaspressosample.viewmodel.docloc.DocLocViewModel
import com.eakurnikov.kaspressosample.viewmodel.main.MainViewModel
import com.eakurnikov.kaspressosample.viewmodel.flaky.FlakyViewModel
import com.eakurnikov.kaspressosample.viewmodel.simple.SimpleViewModel
import com.eakurnikov.kaspressosample.viewmodel.webview.WebViewModel
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
    @ViewModelKey(SimpleViewModel::class)
    fun bindSimpleViewModel(viewModel: SimpleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlakyViewModel::class)
    fun bindFlakyViewModel(viewModel: FlakyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebViewModel::class)
    fun bindWebViewModel(viewModel: WebViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DocLocViewModel::class)
    fun bindDocLocViewModel(viewModel: DocLocViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
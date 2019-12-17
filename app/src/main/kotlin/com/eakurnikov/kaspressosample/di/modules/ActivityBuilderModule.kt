package com.eakurnikov.kaspressosample.di.modules

import com.eakurnikov.common.di.annotations.ActivityScope
import com.eakurnikov.kaspressosample.view.docloc.DocLocActivity
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.eakurnikov.kaspressosample.view.posts.PostsActivity
import com.eakurnikov.kaspressosample.view.flaky.FlakyActivity
import com.eakurnikov.kaspressosample.view.simple.SecondActivity
import com.eakurnikov.kaspressosample.view.simple.SimpleActivity
import com.eakurnikov.kaspressosample.view.webview.WebViewActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module(includes = [AndroidInjectionModule::class])
interface ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindSimpleActivity(): SimpleActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindSecondActivity(): SecondActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindFlakyActivity(): FlakyActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindWebViewActivity(): WebViewActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindPostsActivity(): PostsActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun bindDocLocActivity(): DocLocActivity
}
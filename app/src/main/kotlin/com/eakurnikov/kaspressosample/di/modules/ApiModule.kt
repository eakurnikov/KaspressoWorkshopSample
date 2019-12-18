package com.eakurnikov.kaspressosample.di.modules

import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.data.network.api.PostsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module(includes = [NetworkModule::class])
open class ApiModule {

    @Provides
    @AppScope
    open fun providePostsApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)
}
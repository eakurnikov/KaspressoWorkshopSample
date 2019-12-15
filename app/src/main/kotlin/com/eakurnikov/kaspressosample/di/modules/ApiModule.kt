package com.eakurnikov.kaspressosample.di.modules

import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.data.network.PostsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by eakurnikov on 2019-10-07
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @AppScope
    fun providePostsApi(retrofit: Retrofit): PostsApi = retrofit.create(PostsApi::class.java)
}
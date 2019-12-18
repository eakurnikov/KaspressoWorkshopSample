package com.eakurnikov.kaspressosample.posts

import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.data.network.api.PostsApi
import com.eakurnikov.kaspressosample.di.modules.ApiModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by eakurnikov on 2019-12-18
 */
@Module
class MockedApiModule : ApiModule() {

    @Provides
    @AppScope
    override fun providePostsApi(retrofit: Retrofit): PostsApi = MockedPostsApi()
}
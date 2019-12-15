package com.eakurnikov.kaspressosample.di.modules

import com.eakurnikov.kaspressosample.data.repository.PostsRepo
import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.di.modules.ApiModule
import com.eakurnikov.kaspressosample.di.modules.DaoModule
import com.eakurnikov.kaspressosample.domain.repository.PostsRepoImpl
import dagger.Binds
import dagger.Module

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module(
    includes = [
        ApiModule::class,
        DaoModule::class
    ]
)
interface RepositoryModule {

    @Binds
    @AppScope
    fun bindPostsRepo(impl: PostsRepoImpl): PostsRepo
}
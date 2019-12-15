package com.eakurnikov.kaspressosample.di.modules

import com.eakurnikov.kaspressosample.data.db.KaspressoSampleDatabase
import com.eakurnikov.kaspressosample.data.db.dao.PostsDao
import com.eakurnikov.common.di.annotations.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module(includes = [DatabaseModule::class])
class DaoModule {

    @Provides
    @AppScope
    fun provideMainDao(db: KaspressoSampleDatabase): PostsDao = db.postsDao()
}
package com.eakurnikov.kaspressosample.di.modules

import android.content.Context
import com.eakurnikov.common.annotations.AppContext
import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.domain.app.KaspressoSampleApp
import dagger.Binds
import dagger.Module

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module
interface AppModule {

    @Binds
    @AppScope
    @AppContext
    fun bindContext(app: KaspressoSampleApp): Context
}
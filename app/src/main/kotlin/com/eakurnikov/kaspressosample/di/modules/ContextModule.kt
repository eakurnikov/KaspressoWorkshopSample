package com.eakurnikov.kaspressosample.di.modules

import android.content.Context
import com.eakurnikov.common.annotations.AppContext
import com.eakurnikov.common.di.annotations.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by eakurnikov on 2019-12-15
 */
@Module
class ContextModule(
    @AppContext private val context: Context
) {
    @Provides
    @AppScope
    @AppContext
    fun provideContext(): Context = context
}
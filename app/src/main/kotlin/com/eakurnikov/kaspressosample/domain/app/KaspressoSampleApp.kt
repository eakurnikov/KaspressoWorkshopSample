package com.eakurnikov.kaspressosample.domain.app

import com.eakurnikov.kaspressosample.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by eakurnikov on 2019-12-15
 */
open class KaspressoSampleApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out KaspressoSampleApp> {
        return DaggerAppComponent.builder().create(this)
    }
}
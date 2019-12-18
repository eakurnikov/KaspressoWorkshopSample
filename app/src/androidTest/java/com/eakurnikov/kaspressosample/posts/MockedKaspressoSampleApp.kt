package com.eakurnikov.kaspressosample.posts

import com.eakurnikov.kaspressosample.di.components.DaggerAppComponent
import com.eakurnikov.kaspressosample.di.modules.ApiModule
import com.eakurnikov.kaspressosample.domain.app.KaspressoSampleApp
import dagger.android.AndroidInjector

/**
 * Created by eakurnikov on 2019-12-18
 */
class MockedKaspressoSampleApp : KaspressoSampleApp() {
    override fun applicationInjector(): AndroidInjector<out KaspressoSampleApp> =
        DaggerAppComponent
            .builder()
            .forceSet("apiModule", MockedApiModule() as ApiModule)
            .create(this)
}
package com.eakurnikov.kaspressosample.di.components

import com.eakurnikov.common.di.annotations.AppScope
import com.eakurnikov.kaspressosample.di.modules.ActivityBuilderModule
import com.eakurnikov.kaspressosample.di.modules.AppModule
import com.eakurnikov.kaspressosample.di.modules.RepositoryModule
import com.eakurnikov.kaspressosample.di.modules.ViewModelModule
import com.eakurnikov.kaspressosample.domain.app.KaspressoSampleApp
import dagger.Component
import dagger.android.AndroidInjector

/**
 * Created by eakurnikov on 2019-12-15
 */
@AppScope
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<KaspressoSampleApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<KaspressoSampleApp>()
}
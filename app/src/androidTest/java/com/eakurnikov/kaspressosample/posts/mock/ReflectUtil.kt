package com.eakurnikov.kaspressosample.posts.mock

import com.eakurnikov.kaspressosample.di.components.AppComponent

/**
 * Created by eakurnikov on 2019-12-18
 */
fun <T> AppComponent.Builder.forceSet(field: String, value: T): AppComponent.Builder =
    apply {
        javaClass
            .getDeclaredField(field)
            .apply { isAccessible = true }
            .set(this, value)
    }
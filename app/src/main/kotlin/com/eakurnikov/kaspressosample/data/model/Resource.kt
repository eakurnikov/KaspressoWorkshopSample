package com.eakurnikov.kaspressosample.data.model

/**
 * Created by eakurnikov on 2019-12-15
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()
    class Error<T>(val message: String, val data: T? = null) : Resource<T>()
}
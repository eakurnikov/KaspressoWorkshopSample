package com.eakurnikov.common.di.initializer

/**
 * Created by eakurnikov on 2019-12-15
 */
abstract class ComponentInitializer<P, T> {

    @Volatile
    private var component: T? = null

    fun initAndGet(param: P): T {
        init(param)
        return get()
    }

    fun get(): T = component ?: throw IllegalStateException("Component was not initialized")

    fun dispose() {
        component = null
    }

    protected abstract fun buildComponent(param: P): T

    private fun init(param: P) {
        component ?: synchronized(this) {
            component ?: also { component = buildComponent(param) }
        }
    }
}
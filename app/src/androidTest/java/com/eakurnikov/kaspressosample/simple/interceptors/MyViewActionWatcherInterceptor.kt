package com.eakurnikov.kaspressosample.simple.interceptors

import android.util.Log
import android.view.View
import androidx.test.espresso.ViewAction
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor

class MyViewActionWatcherInterceptor : ViewActionWatcherInterceptor {

    override fun intercept(viewAction: ViewAction, view: View) {
        Log.i("WORKSHOP", "I am ${viewAction.description}")
    }
}
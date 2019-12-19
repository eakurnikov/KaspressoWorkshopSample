package com.eakurnikov.kaspressosample.webview.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.web.KWebView
import com.eakurnikov.kaspressosample.R

/**
 * Created by eakurnikov on 2019-12-18
 */
object WebViewScreen : Screen<WebViewScreen>() {

    val webView = KWebView { withId(R.id.webview) }
}
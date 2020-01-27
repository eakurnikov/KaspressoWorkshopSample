package com.eakurnikov.kaspressosample.view.webview

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.eakurnikov.kaspressosample.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by eakurnikov on 2019-12-15
 */
class WebViewActivity : DaggerAppCompatActivity() {

    companion object {
        private const val URL: String = "https://my.kaspersky.com/en/"

        fun start(context: Context): Unit =
            context.startActivity(Intent(context, WebViewActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        AndroidInjection.inject(this)
        initViews()
    }

    private fun initViews() {
        with(webview) {
            webViewClient = object : WebViewClient() {

                @TargetApi(android.os.Build.VERSION_CODES.N)
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    view.loadUrl(request.url.toString())
                    return true
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
            settings.javaScriptEnabled = true
            loadUrl(URL)
        }
    }
}
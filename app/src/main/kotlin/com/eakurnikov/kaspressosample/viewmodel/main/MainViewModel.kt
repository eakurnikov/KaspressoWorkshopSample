package com.eakurnikov.kaspressosample.viewmodel.main

import android.content.Context
import android.view.View
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.docloc.DocLocActivity
import com.eakurnikov.kaspressosample.view.posts.PostsActivity
import com.eakurnikov.kaspressosample.view.flaky.FlakyActivity
import com.eakurnikov.kaspressosample.view.simple.SimpleActivity
import com.eakurnikov.kaspressosample.view.webview.WebViewActivity
import com.eakurnikov.kaspressosample.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by eakurnikov on 2019-12-15
 */
class MainViewModel @Inject constructor() : BaseViewModel() {

    fun onButtonClick(context: Context, view: View?) {
        when (view?.id) {
            R.id.btn_simple_screen -> SimpleActivity.start(context)
            R.id.btn_flaky_screen -> FlakyActivity.start(context)
            R.id.btn_webview_screen -> WebViewActivity.start(context)
            R.id.btn_posts_screen -> PostsActivity.start(context)
            R.id.btn_doc_loc_screen -> DocLocActivity.start(context)
        }
    }
}
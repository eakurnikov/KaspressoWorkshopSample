package com.eakurnikov.kaspressosample.simple.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.eakurnikov.kaspressosample.R

object MainScreen : Screen<MainScreen>() {

    val title = KTextView { withId(R.id.tv_main_title) }

    val toSimpleScreenBtn = KButton { withId(R.id.btn_simple_screen) }

    val toFlakyScreenBtn = KButton { withId(R.id.btn_flaky_screen) }

    val toWebViewScreenBtn = KButton { withId(R.id.btn_webview_screen) }

    val toPostsScreenBtn = KButton { withId(R.id.btn_posts_screen) }

    val toDocLocScreenBtn = KButton { withId(R.id.btn_doc_loc_screen) }
}
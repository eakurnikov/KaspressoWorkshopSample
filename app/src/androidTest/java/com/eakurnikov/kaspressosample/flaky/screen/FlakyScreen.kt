package com.eakurnikov.kaspressosample.flaky.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.scroll.KScrollView
import com.agoda.kakao.text.KButton
import com.eakurnikov.kaspressosample.R

/**
 * Created by eakurnikov on 2019-12-18
 */
object FlakyScreen : Screen<FlakyScreen>() {

    val scrollView = KScrollView { withId(R.id.scroll_view_flaky) }

    val btn1 = KButton { withId(R.id.btn_flaky_1) }

    val btn3 = KButton { withId(R.id.btn_flaky_4) }

    val flakyBtn = KButton { withId(R.id.btn_flaky_6) }

    val flakyTextView = KButton { withId(R.id.tv_flaky_2) }
}
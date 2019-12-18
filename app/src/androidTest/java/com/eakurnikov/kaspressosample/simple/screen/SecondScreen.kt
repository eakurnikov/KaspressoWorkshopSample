package com.eakurnikov.kaspressosample.simple.screen

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.eakurnikov.kaspressosample.R

/**
 * Created by eakurnikov on 2019-12-18
 */
object SecondScreen : Screen<SecondScreen>() {

    val title = KTextView { withId(R.id.tv_second_title) }
}
package com.eakurnikov.kaspressosample.simple.matchers

import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by eakurnikov on 2019-12-18
 */
open class ViewSizeMatcher private constructor(
    private val expectedWidth: Float,
    private val expectedHeight: Float
) : TypeSafeMatcher<View>(View::class.java) {

    companion object {
        fun withWidthAndHeight(width: Float, height: Float) = ViewSizeMatcher(width, height)
    }

    override fun matchesSafely(item: View): Boolean =
        item.width.toFloat() == expectedWidth && item.height.toFloat() == expectedHeight

    override fun describeTo(description: Description?) {
        description?.appendText("with width $expectedWidth and height $expectedHeight")
    }
}
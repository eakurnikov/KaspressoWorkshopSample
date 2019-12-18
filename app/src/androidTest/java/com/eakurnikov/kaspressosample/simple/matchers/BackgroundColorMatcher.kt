package com.eakurnikov.kaspressosample.simple.matchers

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by eakurnikov on 2019-12-18
 */
class BackgroundColorMatcher(
    @ColorRes private val colorRes: Int = -1,
    private val colorCode: String? = null
) : TypeSafeMatcher<View>() {

    companion object {
        fun withBackgroundColor(@ColorRes colorRes: Int) = BackgroundColorMatcher(colorRes)
    }

    override fun matchesSafely(item: View?): Boolean {
        if (colorRes == -1 && colorCode.isNullOrEmpty()) {
            return item?.background == null
        }

        return item?.let {
            val expectedColor = if (colorRes != -1) {
                ContextCompat.getColor(it.context, colorRes)
            } else {
                Color.parseColor(colorCode)
            }

            it.background != null &&
                    it.background.current is ColorDrawable &&
                    (it.background.current as ColorDrawable).color == expectedColor
        } ?: false
    }

    override fun describeTo(description: Description) {
        description.appendText("with background color: $colorRes or $colorCode")
    }
}
package com.eakurnikov.kaspressosample.simple.matchers

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by eakurnikov on 2019-12-18
 */
class ClassNameMatcher(
    private val expectedClassName: String
) : TypeSafeMatcher<String>() {

    override fun matchesSafely(item: String): Boolean =
        item == expectedClassName

    override fun describeTo(description: Description?) {
        description?.appendText("with class name $expectedClassName")
    }
}
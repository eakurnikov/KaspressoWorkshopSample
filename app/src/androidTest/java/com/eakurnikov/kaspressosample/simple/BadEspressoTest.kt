package com.eakurnikov.kaspressosample.simple

import android.Manifest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.matchers.BackgroundColorMatcher.Companion.withBackgroundColor
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eakurnikov on 2019-12-18
 */
@RunWith(AndroidJUnit4::class)
class BadEspressoTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun badEspressoTest() {
        activityTestRule.launchActivity(null)

        onView(withId(R.id.tv_main_title))
            .check(matches(withText(R.string.main_title)))
            .check(matches(hasTextColor(R.color.colorPrimary)))

        onView(withId(R.id.btn_simple_screen))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(withText(R.string.simple_screen)))
            .check(matches(isClickable()))
            .perform(ViewActions.click())

        onView(withId(R.id.tv_simple_title))
            .check(matches(withText(R.string.simple_title)))
            .check(matches(hasTextColor(R.color.colorPrimary)))

        onView(withId(R.id.et_simple))
            .check(matches(withHint(R.string.simple_hint)))
            .check(matches(withText("")))
            .perform(typeText("Kaspresso"))
            .check(matches(withText("Kaspresso")))

        onView(isRoot()).perform(closeSoftKeyboard())

        onView(
            allOf(
                isDescendantOfA(withId(R.id.simple_root)),
                hasSibling(withText(R.string.delete)),
                withBackgroundColor(R.color.colorPrimary)
            )
        )
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(withText(R.string.next)))
            .check(matches(isClickable()))
            .perform(ViewActions.click())

        onView(withId(R.id.tv_second_title))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(hasTextColor(R.color.colorPrimary)))
            .check(matches(withText("Kaspresso")))

        onView(isRoot())
            .perform(ViewActions.pressBack())

        onView(withId(R.id.et_simple))
            .check(matches(withText("Kaspresso")))

        onView(withId(R.id.btn_simple_delete))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(withText(R.string.delete)))
            .check(matches(isClickable()))
            .perform(ViewActions.click())

        onView(withId(R.id.et_simple))
            .check(matches(withText("")))

        onView(
            allOf(
                isDescendantOfA(withId(R.id.simple_root)),
                hasSibling(withText(R.string.delete)),
                withBackgroundColor(R.color.colorPrimary)
            )
        ).perform(ViewActions.click())

        onView(withId(R.id.tv_second_title))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(hasTextColor(R.color.colorPrimary)))
            .check(matches(withText("")))
    }
}
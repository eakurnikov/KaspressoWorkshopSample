package com.eakurnikov.kaspressosample.simple

import android.Manifest
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.matchers.ClassNameMatcher
import com.eakurnikov.kaspressosample.simple.matchers.ViewSizeMatcher.Companion.withWidthAndHeight
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.simple.screen.SecondScreen
import com.eakurnikov.kaspressosample.simple.screen.SimpleScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eakurnikov on 2019-12-18
 */
@RunWith(AndroidJUnit4::class)
class BetterKakaoTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun betterKakaoTest() {
        activityTestRule.launchActivity(null)

        MainScreen {
            title.hasText(R.string.main_title)
            title.hasTextColor(R.color.colorPrimary)

            toSimpleScreenBtn {
                isVisible()
                withText(R.string.simple_screen)
                matches {
                    withClassName(ClassNameMatcher("androidx.appcompat.widget.AppCompatButton"))
                    withMatcher(Matchers.not(withWidthAndHeight(42f, 42f)))
                }
                isClickable()
                click()
            }
        }

        SimpleScreen {
            title {
                hasText(R.string.simple_title)
                hasTextColor(R.color.colorPrimary)
            }

            editText {
                hasHint(R.string.simple_hint)
                hasEmptyText()
                typeText("Kaspresso")
                hasText("Kaspresso")
            }

            closeSoftKeyboard()

            btnNext {
                isVisible()
                hasText(R.string.next)
                isClickable()
                click()
            }
        }

        SecondScreen {
            title {
                isVisible()
                hasTextColor(R.color.colorPrimary)
                hasText("Kaspresso")
            }

            pressBack()
        }

        SimpleScreen {
            editText.hasText("Kaspresso")

            btnDelete {
                isVisible()
                hasText(R.string.delete)
                isClickable()
                click()
            }

            editText.hasEmptyText()

            btnNext.click()
        }

        SecondScreen {
            title {
                isVisible()
                hasTextColor(R.color.colorPrimary)
                hasText("")
            }
        }
    }
}
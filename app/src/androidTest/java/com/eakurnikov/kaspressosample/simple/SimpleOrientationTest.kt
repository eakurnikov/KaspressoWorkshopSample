package com.eakurnikov.kaspressosample.simple

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.simple.screen.SimpleScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eakurnikov on 2019-12-18
 */
@RunWith(AndroidJUnit4::class)
class SimpleOrientationTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun simpleOrientationTest() {
        before {
            activityTestRule.launchActivity(null)
            device.exploit.setOrientation(Exploit.DeviceOrientation.Portrait)
        }.after {
            device.exploit.setOrientation(Exploit.DeviceOrientation.Landscape)
        }.run {
            step("Open Simple screen") {
                MainScreen {
                    title {
                        hasText(R.string.main_title)
                        hasTextColor(R.color.colorPrimary)
                    }

                    toSimpleScreenBtn {
                        isVisible()
                        hasText(R.string.simple_screen)
                        isClickable()
                        click()
                    }
                }
            }

            step("Type \"Kaspresso\"") {
                SimpleScreen {
                    title {
                        hasText(R.string.simple_title)
                        hasTextColor(R.color.colorPrimary)
                    }

                    btnDelete {
                        isVisible()
                        hasText(R.string.delete)
                        isClickable()
                        click()
                    }

                    editText {
                        hasHint(R.string.simple_hint)
                        hasEmptyText()
                        typeText("Kaspresso")
                        hasText("Kaspresso")
                    }

                    closeSoftKeyboard()
                }
            }

            step("Rotate device 5 times") {
                for (i in 1..5) device.exploit.rotate()
            }

            step("Check \"Kaspresso\" is still displayed") {
                SimpleScreen {
                    editText {
                        hasHint(R.string.simple_hint)
                        hasText("Kaspresso")
                    }
                }
            }
        }
    }
}
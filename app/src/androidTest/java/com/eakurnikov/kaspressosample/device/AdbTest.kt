package com.eakurnikov.kaspressosample.device

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdbTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun adbTest() {
        before {
            activityTestRule.launchActivity(null)
            /**
             * Some action to prepare the state
             */
        }.after {
            /**
             * Some action to revert the state
             */
        }.run {
            step("Open Simple screen") {
                MainScreen {
                    title.hasText(R.string.main_title)
                    title.hasTextColor(R.color.colorPrimary)

                    toSimpleScreenBtn {
                        isVisible()
                        hasText(R.string.simple_screen)
                        isClickable()
                        click()
                    }
                }
            }

            step("Execute command on host") {
                val result = adbServer.performCmd("hostname")
                Assert.assertTrue(result.isNotEmpty())
            }

            step("Execute ADB Shell command") {
                val command = "pm list packages"

                val result = adbServer.performShell(command)
                Assert.assertTrue("package:${device.targetContext.packageName}" in result.first())
            }

            step("Execute ADB command") {
                val command = "undefined_command"

                try {
                    adbServer.performAdb(command)
                } catch (ex: AdbServerException) {
                    Assert.assertTrue("unknown command $command" in ex.message)
                }
            }
        }
    }
}
package com.eakurnikov.kaspressosample.webview

import android.Manifest
import androidx.test.espresso.web.webdriver.DriverAtoms
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.eakurnikov.kaspressosample.webview.screen.WebViewScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eakurnikov on 2019-12-18
 */
@RunWith(AndroidJUnit4::class)
class WebViewTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun webViewTest() {
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
            step("Open WebView screen") {
                MainScreen {
                    title.hasText(R.string.main_title)
                    title.hasTextColor(R.color.colorPrimary)

                    toWebViewScreenBtn {
                        isVisible()
                        hasText(R.string.webview_screen)
                        isClickable()
                        click()
                    }
                }
            }

            step("Find \"Sign in\" button") {
                WebViewScreen {
                    webView {
                        withElement(
                            Locator.CLASS_NAME,
                            "btn"
                        ) {
                            containsText("Sign in")
                            web.withElement(ref).perform(DriverAtoms.getText())
                        }

                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/header/section/div[3]/div[2]/button"
                        ) {
                            hasText("Sign in")
                        }

                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/section[1]/div/div/h2"
                        ) {
                            containsText("Protect your data")
                        }
                    }
                }
            }

            step("Click \"Get support\" button") {
                WebViewScreen {
                    webView {
                        withElement(
                            Locator.XPATH,
                            "//*[@id=\"app\"]/section[5]/div/div/div[2]/div[3]/button"
                        ) {
                            hasText("Get Support")
                            Screen.idle(2_000)
                            click()
                        }
                    }
                }
            }
        }
    }
}
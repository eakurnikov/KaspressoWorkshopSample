package com.eakurnikov.kaspressosample.posts

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.posts.screen.PostsScreen
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by eakurnikov on 2019-12-18
 */
@RunWith(AndroidJUnit4::class)
class PostsOfflineTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun postsTest() {
        before {
            activityTestRule.launchActivity(null)
            device.network.disable()
        }.after {
            device.network.enable()
        }.run {
            step("Open Posts screen") {
                MainScreen {
                    title.hasText(R.string.main_title)
                    title.hasTextColor(R.color.colorPrimary)

                    toPostsScreenBtn {
                        isVisible()
                        hasText(R.string.posts_screen)
                        isClickable()
                        click()
                    }
                }
            }

            step("Check list is fine") {
                PostsScreen {
                    postsList.isGone()

                    errorTextView {
                        isVisible()
                        hasAnyText()
                    }
                }
            }
        }
    }
}
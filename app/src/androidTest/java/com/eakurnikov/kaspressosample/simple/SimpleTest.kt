package com.eakurnikov.kaspressosample.simple

import android.Manifest
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.matchers.ViewSizeMatcher.Companion.withWidthAndHeight
import com.eakurnikov.kaspressosample.simple.scenario.TypeTextAndCheckTitleScenario
import com.eakurnikov.kaspressosample.simple.screen.MainScreen
import com.eakurnikov.kaspressosample.simple.screen.SecondScreen
import com.eakurnikov.kaspressosample.simple.screen.SimpleScreen
import com.eakurnikov.kaspressosample.view.main.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun perfectKaspressoTest() {
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
                        isClickable()
                        matches {
                            withText(R.string.simple_screen)
                            withMatcher(Matchers.not(withWidthAndHeight(42f, 42f)))
                        }
                        click()
                    }
                }

                scenario(TypeTextAndCheckTitleScenario("1"))
                scenario(TypeTextAndCheckTitleScenario(" "))
                scenario(TypeTextAndCheckTitleScenario("Kaspresso"))
            }
        }
    }

    @Test
    fun goodKaspressoTest() {
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
                        isClickable()
                        matches {
                            withText(R.string.simple_screen)
                            withMatcher(Matchers.not(withWidthAndHeight(42f, 42f)))
                        }
                        click()
                    }
                }
            }

            step("Type \"Kaspresso\" and open Second screen") {
                SimpleScreen {
                    title {
                        hasText(R.string.simple_title)
                        hasTextColor(R.color.colorPrimary)
                    }

                    editText {
                        hasHint(R.string.simple_hint)
                        hasEmptyText()
                        typeText("tratata")
                        hasText("tratata")
                    }

                    closeSoftKeyboard()

                    btnDelete {
                        isClickable()
                        click()
                    }

                    editText {
                        hasEmptyText()
                        typeText("Kaspresso")
                    }

                    closeSoftKeyboard()

                    btnNext {
                        click()
                    }
                }
            }

            step("Check \"Kaspresso\" is displayed") {
                SecondScreen {
                    title {
                        isDisplayed()
                        isVisible()
                        hasText("Kaspresso")
                    }
                }
            }
        }
    }

    @Test
    fun badKakaoTest() {
        activityTestRule.launchActivity(null)

        MainScreen {
            title.hasText(R.string.main_title)
            title.hasTextColor(R.color.colorPrimary)

            toSimpleScreenBtn {
                isVisible()
                isClickable()
                matches {
                    withText(R.string.simple_screen)
                    withMatcher(Matchers.not(withWidthAndHeight(42f, 42f)))
                }
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
                typeText("tratata")
                hasText("tratata")
            }

            closeSoftKeyboard()

            btnDelete {
                isClickable()
                click()
            }

            editText {
                hasEmptyText()
                typeText("Kaspresso")
            }

            closeSoftKeyboard()

            btnNext {
                click()
            }
        }
    }

    @Test
    fun badEspressoTest() {
        activityTestRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.tv_main_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.main_title)))
            .check(ViewAssertions.matches(ViewMatchers.hasTextColor(R.color.colorPrimary)))

        Espresso.onView(ViewMatchers.withId(R.id.btn_simple_screen))
            .check(ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))
            )
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.simple_screen)))
            .check(ViewAssertions.matches(Matchers.not(withWidthAndHeight(42f, 42f))))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tv_simple_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.simple_title)))
            .check(ViewAssertions.matches(ViewMatchers.hasTextColor(R.color.colorPrimary)))

        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
    }
}
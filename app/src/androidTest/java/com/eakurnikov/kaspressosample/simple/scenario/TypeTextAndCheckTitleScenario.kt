package com.eakurnikov.kaspressosample.simple.scenario

import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.screen.SecondScreen
import com.eakurnikov.kaspressosample.simple.screen.SimpleScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

/**
 * Created by eakurnikov on 2019-12-18
 */
class TypeTextAndCheckTitleScenario(
    private val text: String
) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        testLogger.i("The given text is \"$text\"")

        step("Type \"$text\" and open Second screen") {
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
                    typeText(text)
                    hasText(text)
                }

                closeSoftKeyboard()

                btnNext {
                    isVisible()
                    hasText(R.string.next)
                    isClickable()
                    click()
                }
            }
        }

        step("Check \"$text\" is displayed and return to Simple screen") {
            SecondScreen {
                title {
                    isVisible()
                    hasTextColor(R.color.colorPrimary)
                    hasText(text)
                }

                pressBack()
            }
        }
    }
}
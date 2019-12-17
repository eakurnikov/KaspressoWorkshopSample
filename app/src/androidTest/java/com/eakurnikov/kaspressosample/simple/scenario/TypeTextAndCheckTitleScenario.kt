package com.eakurnikov.kaspressosample.simple.scenario

import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.simple.screen.SecondScreen
import com.eakurnikov.kaspressosample.simple.screen.SimpleScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class TypeTextAndCheckTitleScenario(
    private val text: String
) : Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("Type \"$text\" and open Second screen") {
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
                    typeText(text)
                }

                closeSoftKeyboard()

                btnNext {
                    click()
                }
            }
        }

        step("Check \"$text\" is displayed") {
            SecondScreen {
                title {
                    isDisplayed()
                    isVisible()
                    hasText(text)
                }

                pressBack()
            }

            SimpleScreen.btnDelete.click()
        }
    }
}
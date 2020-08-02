package com.kotlin.training.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.training.R
import com.kotlin.training.view.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class LoginActivityUITest {

    @get:Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)


    @Test
    fun ensureUsernameValidationWork() {
        onView(withId(R.id.et_username))
            .perform(typeText(""),closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click(), closeSoftKeyboard())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.snack_username)))

    }

    @Test
    fun ensurePasswordValidationWork() {
        onView(withId(R.id.et_username))
            .perform(typeText("Test"),closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(""),closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click(), closeSoftKeyboard())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.snack_password)))

    }

    @Test
    fun ensureLoginWork() {
        onView(withId(R.id.et_username))
            .perform(typeText("Test"),closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText("Test"),closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click(), closeSoftKeyboard())

    }

}
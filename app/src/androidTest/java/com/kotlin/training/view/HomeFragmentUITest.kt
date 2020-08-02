package com.kotlin.training.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.training.R
import com.kotlin.training.view.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class HomeFragmentUITest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun test_isFragmentInView() {
        onView(withId(R.id.parentLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun test_isButtonInView() {
        onView(withId(R.id.btn_fetch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.btn_fetch)).perform(ViewActions.click())
    }
    @Test
    fun test_isImageInView() {
        onView(withId(R.id.imvSpeak)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imvSpeak)).perform(ViewActions.click())
    }

}
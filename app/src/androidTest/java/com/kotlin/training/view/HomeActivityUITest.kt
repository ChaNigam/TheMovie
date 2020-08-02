package com.kotlin.training.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.training.R
import com.kotlin.training.view.ui.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class HomeActivityUITest {


    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun test_isNavHostDisplayed() {
        onView(withId(R.id.nav_host_fragment)).
                check(matches(isDisplayed()))
    }

}
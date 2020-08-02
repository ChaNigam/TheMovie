package com.kotlin.training.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.kotlin.training.R
import com.kotlin.training.view.ui.HomeActivity
import com.kotlin.training.view.ui.MovieListFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class MovieListFragmentUITest {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun test_isFragmentInView() {
        onView(withId(R.id.parentLayout)).check(matches(isDisplayed()))
    }
    @Test
    fun test_isMovieFragmentInView() {
        val fragment = MovieListFragment()
        activityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, fragment).commit()
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_list)).check(matches(isDisplayed()))
    }
    @Test
    fun ensureFetchValidationWork() {
        onView(withId(R.id.progress)).isVisible()
        onView(withId(R.id.search)).isVisible()
//        if (getRVcount() > 0)
//            onView(withId(R.id.movie_list))
//                .check(matches(hasDescendant(withText("Parasite"))));

    }

//    private fun getRVcount(): Int {
//        val recyclerView =
//                activityRule.getActivity().findViewById(R.id.movie_list) as RecyclerView
//        return recyclerView.adapter!!.itemCount
//    }
//
//    fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)
//
    private fun ViewInteraction.isVisible() = getViewAssertion(Visibility.VISIBLE)

    fun ViewInteraction.isInvisible() = getViewAssertion(Visibility.INVISIBLE)

    private fun getViewAssertion(visibility: Visibility): ViewAssertion? {
        return matches(withEffectiveVisibility(visibility))
    }
}
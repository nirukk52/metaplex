package com.ql.giantbomb.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ql.giantbomb.ApplicationTest
import com.ql.giantbomb.R
import com.ql.giantbomb.base.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SearchMainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SearchMainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testDefaultKeyNFTs() {

        onView(ViewMatchers.withId(R.id.etSearchGames))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etSearchGames))
            .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.default_key)))

        onView(ViewMatchers.withId(R.id.btGo)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.rvGamesList))
            .perform(ViewActions.swipeUp())

        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.rvGamesList)).check(
            ApplicationTest.RecyclerViewItemCountAssertion(
                772
            )
        )
    }

    @Test
    fun testInvalidKeyNFTs() {

        onView(ViewMatchers.withId(R.id.etSearchGames))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.etSearchGames))
            .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.default_key)))

        onView(ViewMatchers.withId(R.id.btGo)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.rvGamesList))
            .perform(ViewActions.swipeUp())

        onView(ViewMatchers.withId(R.id.rvGamesList)).check(
            ApplicationTest.RecyclerViewItemCountAssertion(
                772
            )
        )
    }
}
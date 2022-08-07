/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ql.giantbomb

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ql.giantbomb.base.utils.EspressoIdlingResource
import com.ql.giantbomb.game.GamesActivity
import com.ql.giantbomb.game.ui.search.GamesAdapter
import com.ql.giantbomb.util.DataBindingIdlingResource
import com.ql.giantbomb.util.monitorActivity
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test


class ApplicationTest {

    // An Idling Resource that waits for Data Binding to have no pending bindings
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }


    @Test
    fun runApp() {
        // start up Game screen
        val activityScenario = ActivityScenario.launch(GamesActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.etSearchGames))
            .check(matches(isDisplayed()))

        onView(withId(R.id.etSearchGames))
            .check(matches(withHint(R.string.search_games_here)))

        onView(withId(R.id.etSearchGames))
            .perform(typeText("Call Of Duty"), closeSoftKeyboard())

        onView(withId(R.id.rvGamesList))
            .perform(swipeUp())

        onView(withId(R.id.rvGamesList)).check(RecyclerViewItemCountAssertion(37))

        onView(withId(R.id.rvGamesList))
            .perform(RecyclerViewActions.scrollToPosition<GamesAdapter.GameViewHolder>(15))

        onView(withId(R.id.rvGamesList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<GamesAdapter.GameViewHolder>(
                    15,
                    click()
                )
            )

    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        }

    }
}

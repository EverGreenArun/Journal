package com.arun.journal.ui.fragment.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arun.journal.R
import com.arun.journal.RecyclerViewItemCountAssertion
import com.arun.journal.TestUtils
import com.arun.journal.network.Urls
import com.arun.journal.ui.adapter.JournalsAdapter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun homeFragment_fetching_data_test() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.tv_state)).check(matches(withText(R.string.loading)))
        onView(withId(R.id.recycler)).check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun homeFragment_0_th_item_test() {
        launchFragmentInContainer<HomeFragment>()
        onView(isRoot()).perform(TestUtils.waitFor(30000))
        onView(withId(R.id.recycler))
            .perform(scrollToPosition<JournalsAdapter.JournalItemViewHolder>(0))
            .check(matches(hasDescendant(withText("Beavers"))))
    }

    @Test
    fun homeFragment_data_size_test() {
        launchFragmentInContainer<HomeFragment>()
        onView(isRoot()).perform(TestUtils.waitFor(30000))
        onView(withId(R.id.recycler)).check(RecyclerViewItemCountAssertion(14))
    }

    @Test
    fun homeFragment_14_th_item_test() {
        launchFragmentInContainer<HomeFragment>()
        onView(isRoot()).perform(TestUtils.waitFor(30000))
        onView(withId(R.id.recycler))
            .perform(scrollToPosition<JournalsAdapter.JournalItemViewHolder>(13))
            .check(matches(hasDescendant(withText("Language"))))
    }


    @Test
    fun homeFragment_item_click_test() {
        val mockNavController = mock(NavController::class.java)
        val homeScenario = launchFragmentInContainer<HomeFragment>()
        homeScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(isRoot()).perform(TestUtils.waitFor(30000))
        onView(withId(R.id.recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<JournalsAdapter.JournalItemViewHolder>(
                    0,
                    click()
                )
            )
        onView(isRoot()).perform(TestUtils.waitFor(5000))
        onView(withText("Beavers")).check(matches(isDisplayed()))
    }

    /**
     * Turn off internet before test this case
     * */
    /*@Test
    fun homeFragment_without_internet_error_test() {
        launchFragmentInContainer<HomeFragment>()
        onView(isRoot()).perform(TestUtils.waitFor(10000))
        onView(withId(R.id.tv_state)).check(matches(withText(R.string.no_internet)))
    }*/

    /**
     * Before test error case, have to set wrong value for [Urls.JOURNAL_LIST]
     * */
    /* @Test
     fun homeFragment_api_error_test() {
         launchFragmentInContainer<HomeFragment>()
         onView(isRoot()).perform(TestUtils.waitFor(10000))
         onView(withId(R.id.tv_state)).check(matches(withText(R.string.api_error)))
     }*/
}
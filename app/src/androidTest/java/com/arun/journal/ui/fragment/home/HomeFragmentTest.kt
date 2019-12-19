package com.arun.journal.ui.fragment.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arun.journal.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest{
    @Test
    fun testHomeFragment() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
    }
}
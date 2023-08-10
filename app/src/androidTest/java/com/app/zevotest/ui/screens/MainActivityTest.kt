package com.app.zevotest.ui.screens

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.zevotest.R
import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.ui.UIState.UIState
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLoadingState() {
        Espresso.onView(withId(R.id.progress)).check(matches(isDisplayed()))
    }

    @Test
    fun testSuccessState() {
        val articles = listOf( Article(
            1,
            "test 1",
            "test content",
            "test des",
            "test publised",
            "test title",
            "",
            "test"
        ),
            Article(
                1,
                "test 1",
                "test content",
                "test des",
                "test publised",
                "test title",
                "",
                "test"
            ),
            Article(
                1,
                "test 1",
                "test content",
                "test des",
                "test publised",
                "test title",
                "",
                "test"
            )
        )
        val successState = UIState.Success(articles)


        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerviewPost)).check(matches(isDisplayed()))
    }


}

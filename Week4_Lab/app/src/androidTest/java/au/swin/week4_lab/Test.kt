package au.swin.week4_lab


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test() {
        onView(withId(R.id.mButton2)).perform(click())
        onView(withId(R.id.mTextView)).check(matches(withText("This is Rani")))

        onView(withId(R.id.mButton3)).perform(click())
        onView(withId(R.id.mTextView)).check(matches(withText("This is Texas")))
    }

    @Test
    fun test2() {
        onView(withId(R.id.mButton1)).perform(click())
        onView(withId(R.id.mTextView)).check(matches(withText("This is Spectre")))
        onView(withId(R.id.mImageView)).check(matches())
    }
}

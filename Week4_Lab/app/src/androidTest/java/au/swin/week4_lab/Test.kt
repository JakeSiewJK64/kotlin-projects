package au.swin.week4_lab


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import au.swin.week4_lab.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test() {
        val materialButton = onView(
allOf(withId(R.id.mButton1), withText("Button 1"),
childAtPosition(
allOf(withId(R.id.buttonLinearGroup),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
0),
isDisplayed()))
        materialButton.perform(click())
        
        val imageView = onView(
allOf(withId(R.id.mImageView),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageView.check(matches(isDisplayed()))
        
        val imageView2 = onView(
allOf(withId(R.id.mImageView),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageView2.check(matches(isDisplayed()))
        
        val materialButton2 = onView(
allOf(withId(R.id.mButton2), withText("Button 2"),
childAtPosition(
allOf(withId(R.id.buttonLinearGroup),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)),
1),
isDisplayed()))
        materialButton2.perform(click())
        
        val imageView3 = onView(
allOf(withId(R.id.mImageView),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageView3.check(matches(isDisplayed()))
        
        val textView = onView(
allOf(withId(R.id.mTextView), withText("This is Rani"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView.check(matches(withText("This is Rani")))
        
        val textView2 = onView(
allOf(withId(R.id.mTextView), withText("This is Rani"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView2.check(matches(withText("This is Rani")))
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }

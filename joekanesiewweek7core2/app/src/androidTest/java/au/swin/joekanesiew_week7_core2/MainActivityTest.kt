package au.swin.joekanesiew_week7_core2


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    /**
     * [TEST]: WHETHER CLICKING ON LAYOUT WILL REDIRECT TO DETAIL ACTIVITY.
     */
    @Test
    fun testClickingLayoutWillDirectToDetailActivity() {
        val locationLayout = onView(withId(R.id.kyotoImage))
        val detailLocationActivty = onView(withId(R.id.locationDetailLayout))
        locationLayout.perform(click())
        detailLocationActivty.check(matches(isDisplayed()))
    }

    /**
     * [TEST]: WHETHER CLICKING BACK WILL RETURN TO MAIN MENU.
     */
    @Test
    fun testBackButtonWorks() {
        val locationLayout = onView(withId(R.id.kyotoImage))
        val locationMainMenu = onView(withId(R.id.locationMainMenu))
        locationLayout.perform(click())
        pressBack()
        locationMainMenu.check(matches(isDisplayed()))
    }

    /**
     * [TEST]: WHETHER THE FAVORITE CHECKBOX REMAINS CHECKED EVEN AFTER CLICKING BACK BUTTON.
     */
    @Test
    fun testFavoriteRemainsChecked() {
        val locationLayout = onView(withId(R.id.kyotoImage))
        val myCheckBox = onView(withId(R.id.favoriteLocationCheckbox))
        locationLayout.perform(click())
        myCheckBox.perform(click())
        pressBack()
        locationLayout.perform(click())
        myCheckBox.check(matches(isChecked()))
    }

    /**
     * [TEST]: WHETHER SNACKBAR IS DISPLAYED UPON LOADING THE DETAIL ACTIVITY LAYOUT.
     */
    @Test
    fun testSnackbarDisplayed() {
        val locationLayout = onView(withId(R.id.kyotoImage))
        val mySnackbar = onView(withText("Loaded Kyoto Successfully!"))
        locationLayout.perform(click())
        mySnackbar.check(matches(isDisplayed()))
    }

    /**
     * [TEST]: WHETHER THE EDIT TEXT FOR LOCATION NAME MAINTAINS NEW VALUE AFTER PRESSING BACK BUTTON.
     */
    @Test
    fun testInputEditTextLocationNameMaintains() {
        val locationName = onView(withId(R.id.locationDetailName))
        val locationLayout = onView(withId(R.id.kyotoImage))
        val matchString = "Kyotoooooooooooo"
        locationLayout.perform(click())
        locationName.perform(replaceText(matchString))
        pressBack()
        locationLayout.perform(click())
        locationName.check(matches(withText(matchString)))
    }

    /**
     * [TEST]: WHETHER INPUT VALIDATION FOR DATE FORMAT IS APPLIED AND ERROR SNACKBAR IS DISPLAYED.
     */
    @Test
    fun testInputValidationLocationDate() {
        val locationLayout = onView(withId(R.id.kyotoImage))
        val locationDate = onView(withId(R.id.locationDetailDate))
        val errorSnackbar = onView(withText("Invalid Location Date"))
        locationLayout.perform(click())
        locationDate.perform(replaceText("17/5/2022"))
        pressBack()
        errorSnackbar.check(matches(isDisplayed()))
    }

    /**
     * [TEST]: WHETHER CHANGE IN ACTIVITY 2 IS REFLECTED IN ACTIVITY 1.
     */
    @Test
    fun testActivity2IsReflectedInActivity1() {
        val locationDetailName = onView(withId(R.id.locationDetailName))
        val locationLayout = onView(withId(R.id.kyotoImage))
        val locationName = onView(withId(R.id.kyotoText))
        val matchString = "Kyotoooooooooooo"
        locationLayout.perform(click())
        locationDetailName.perform(replaceText(matchString))
        pressBack()
        locationName.check(matches(withText(matchString)))
    }
}

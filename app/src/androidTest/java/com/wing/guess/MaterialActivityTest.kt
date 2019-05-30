package com.wing.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)


    @Test
    fun guessWrong(){
        val resource = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        for (n in 1..10){
            if (n != secret){
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                onView(withId(R.id.ok_button)).perform(click())

                val message =
                    if (n < secret){
                        resource.getString(R.string.bigger)
                    }else{
                        resource.getString(R.string.smaller)
                    }
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resource.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun checkCounter(){
        val resource = activityTestRule.activity.resources

        onView(withId(R.id.ed_number)).perform(clearText())
        onView(withId(R.id.ed_number)).perform(typeText("9"))
        onView(withId(R.id.ok_button)).perform(click())
        onView(withText(resource.getString(R.string.ok))).perform(click()).perform(closeSoftKeyboard())

        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.count)).check(matches(withText("0")))

    }
}
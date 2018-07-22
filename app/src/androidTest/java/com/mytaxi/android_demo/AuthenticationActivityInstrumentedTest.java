package com.mytaxi.android_demo;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.AuthenticationActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AuthenticationActivityInstrumentedTest {
    String username, password;
    String username1, password1;


    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(
            AuthenticationActivity.class);

    @Before
    public void initLoginCredentials() {
        // Specify a valid string
        username = "crazydog335";
        password = "venture";
        username1 = "don";
        password1 = "password";
    }

    @Test
    public void testEnsureValidLoginOpensSearch() {
        // Type text and then press the button.
        onView(withId(R.id.edt_username))
                .perform(typeText(username));
        onView(withId(R.id.edt_password))
                .perform(typeText(password));
        onView(withId(R.id.btn_login))
                .perform(click());

        // Check that the textSearch field is present.
        //onView(withText(endsWith("mytaxi demo"))).check(matches(isDisplayed()));
        //onView(withText("Sarah Scott")).inRoot(RooMatchers.isPlatformPopup()).perform(click);
           /* onView(withText("Sarah Scott")).check(matches(isDisplayed()));
            onData(withText("Sarah Scott")).inAdapterView(withText("Sarah Scott"))
                    .perform(click());
            onView(withText("Sarah Scott")).check(matches(withText("Sarah Scott")))
                    .check(matches(isDisplayed())); */
    }


    }





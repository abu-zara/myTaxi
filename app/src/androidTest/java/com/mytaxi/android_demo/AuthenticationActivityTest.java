package com.mytaxi.android_demo;


import android.support.annotation.StringRes;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.AuthenticationActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthenticationActivityTest {
    private String username, password, invalidUsername, invalidPassword;
    private final String secondSearchResult = "Sarah Scott";

    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(
            AuthenticationActivity.class);

    @Before
    public void initLoginCredentials() {
        // Specify a valid string
        username = "crazydog335";
        password = "venture";
        invalidUsername = "wronguser";
        invalidPassword = "wrongpass";
    }

    @Test
    public void test1LoginWithEmptyFields() throws InterruptedException {
        // Type text and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());

        // Best to use Espresso Idling Resource but not working yet
        Thread.sleep(4000);
        checkSnackBarDisplayedByMessage(R.string.message_login_fail);
    }

    @Test
    public void test2LoginWithNoPassword() throws InterruptedException {
        // Type text and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(clearText());
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(4000);
        checkSnackBarDisplayedByMessage(R.string.message_login_fail);
    }

    @Test
    public void test3LoginWithNoUsername() throws InterruptedException {
        // Type text and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(clearText());
        onView(withId(R.id.edt_password))
                .perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());
        Thread.sleep(4000);
        checkSnackBarDisplayedByMessage(R.string.message_login_fail);
    }

    @Test
    public void test4LoginWithInvalidUsername() throws InterruptedException {
        // Type text and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(typeText(invalidUsername), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());
        Thread.sleep(4000);
        checkSnackBarDisplayedByMessage(R.string.message_login_fail);
    }

    @Test
    public void test5LoginWithInvalidPassword() throws InterruptedException {
        // Type text and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText(invalidPassword), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());

        Thread.sleep(4000);
        checkSnackBarDisplayedByMessage(R.string.message_login_fail);
    }

    @Test
    public void test6ValidLoginSuccess() throws InterruptedException {
        // Type username and password and then press the login button.
        onView(withId(R.id.edt_username))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login))
                .perform(click());

        Thread.sleep(5000);
        // Check that the textSearch field is present.
        onView(withId(R.id.textSearch))
                .check(matches(isDisplayed()));
    }

    private void checkSnackBarDisplayedByMessage(@StringRes int message) {
        onView(withText(message))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }
}




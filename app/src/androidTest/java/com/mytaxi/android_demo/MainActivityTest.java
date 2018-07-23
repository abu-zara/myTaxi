package com.mytaxi.android_demo;

import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AnyOf.anyOf;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private String searchTerm;
    private final String secondSearchResult = "Sarah Scott";
    private static final String VALID_PHONE_NUMBER = "413-868-2228";
    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + VALID_PHONE_NUMBER);
    private static String PACKAGE_ANDROID_DIALER = "com.android.phone";

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    private MainActivity mActivity = null;

    @Before
    public void setActivity() {
        // Set Activity
        mActivity = mActivityRule.getActivity();
    }

    @Before
    public void initSearchVariable() {
        // Specify search term
        searchTerm = "sa";
    }

    @Test
    public void searchTextViewDisplayed() {
        // Mock Logged  in state
        onView(withId(R.id.textSearch))
                .check(matches(isDisplayed()));

        onView(withHint("Search driver here"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void successAutoCompleteList() {
        // Type search term to trigger  suggestions.
        onView(withId(R.id.textSearch))
                .perform(typeText(searchTerm));

        // Verify the second result in the list
        onView(withText(secondSearchResult))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void viewDriver() {
        // Type search term to trigger  suggestions.
        onView(withId(R.id.textSearch))
                .perform(typeText(searchTerm));

        // Verify the second result in the list
        onView(withText(secondSearchResult))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // Verify driver profile
        onView(withId(R.id.textViewDriverName))
                .check(matches(isDisplayed()));
        onView(withId(R.id.imageViewDriverAvatar))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textViewDriverLocation))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textViewDriverDate))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fab))
                .check(matches(isDisplayed()));
    }

    @Test
    public void callIconVisible() {
        // Type search term to trigger  suggestions.
        // Type text and then press the button.
        onView(withId(R.id.textSearch))
                .perform(typeText(searchTerm));

        // Click secondSearchResult
        onView(withText(secondSearchResult))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // verify that the dialer icon shows
        onView(withId(R.id.fab))
                .check(matches(isDisplayed()));
    }

    @Test
    public void callDriver() {

        // Type search term to trigger  suggestions.
        onView(withId(R.id.textSearch))
                .perform(typeText(searchTerm));

        // Click secondSearchResult
        onView(withText(secondSearchResult))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());

        // Click on the dialer icon
        onView(withId(R.id.fab))
                .perform(click());

        intended(anyOf(
                hasAction(Intent.ACTION_CALL),
                hasData(INTENT_DATA_PHONE_NUMBER),
                toPackage(PACKAGE_ANDROID_DIALER)
        ));
    }
}
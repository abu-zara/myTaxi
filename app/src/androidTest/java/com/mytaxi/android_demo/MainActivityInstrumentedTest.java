package com.mytaxi.android_demo;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.meta.When;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    String searchQuery;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void typetext() {
        // Specify a valid string.
        searchQuery = "sa";
    }
}
   /* @Test
    public void toolbarTitle(){
        CharSequence title = InstrumentationRegistry.getTargetContext().getString(R.string.my_title);
        matchToolbarTitle(title);
    }
    private static ViewInteraction matchToolbarTitle(CharSequence title){
        return onView(allOf(
                isAssignableFrom(TextView.class)),
        withParent(isAssignableFrom(Toolbar.class
        )).check(matches(withText(title.toString()))));
    }



    public void testSearchResultsLoaded() {
        // Check that the text was changed.
        onView(withId(R.id.textSearch)).perform(typeText(searchQuery));
        onView(withText("Sarah Scott")).perform(click());
    }
}*/

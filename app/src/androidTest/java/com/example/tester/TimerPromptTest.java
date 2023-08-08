package com.example.tester;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class TimerPromptTest {
    @Rule
    public ActivityScenarioRule<RegisterUser> activityRule =
            new ActivityScenarioRule<>(RegisterUser.class);

    private TimerIdlingResource timerIdlingResource;

    @Before
    public void setUp() {
        timerIdlingResource = new TimerIdlingResource(1); // 1 second timeout
        IdlingRegistry.getInstance().register(timerIdlingResource);
    }


    @Test
    public void testTimerPrompt() {
        // Simulate a successful registration scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .perform(ViewActions.typeText("testuser"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.retype_password))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.register))
                .perform(ViewActions.click());

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("SIGN-IN"))
                .perform(ViewActions.click());

        // Simulate a successful registration scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_signin))
                .perform(ViewActions.typeText("testuser"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_signin))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.signin))
                .perform(ViewActions.click());

        // Perform action in the prompt (e.g., clicking a button)
        Espresso.onView(ViewMatchers.withText("PROCEED TO TIMER"))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.startPauseButton))
                .perform(ViewActions.click());

        // Fast-forward time to reach the TimerPrompt
        timerIdlingResource.fastForwardTime((25 * 60 * 1000) + 1);

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Do you want to continue working?"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(timerIdlingResource);
    }
}
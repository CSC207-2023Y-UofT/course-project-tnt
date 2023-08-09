package com.example.tester;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * This class contains instrumented UI tests for the prompt functionality tied to sign-in features.
 *
 * It tests different scenarios of sign-in attempts including successful sign-in, sign-in with
 * invalid credentials, and sign-in with missing fields using the Espresso testing framework.
 *
 * This class is annotated with the following annotations:
 * - {@code @RunWith(AndroidJUnit4ClassRunner.class)}:
 * Specifies that this test class should be run with the AndroidJUnit4 runner.
 * - {@code @FixMethodOrder(MethodSorters.NAME_ASCENDING)}:
 * Fixes the order of test methods to run in ascending alphabetical order.
 * - {@code @LargeTest}:
 * Indicates that this test class contains large tests that may require longer execution time.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class SigninPromptInstrumentedTest {
    /**
     * Rule to launch the RegisterUser activity for testing
     */
    @Rule
    public ActivityScenarioRule<RegisterUser> activityRule =
            new ActivityScenarioRule<>(RegisterUser.class);

    /**
     * Test case for successful sign-in prompt.
     *
     * This test simulates a successful registration scenario and performs the sign-in action with
     * valid credentials. Then, it verifies that the prompt for successful sign-in is displayed.
     */
    @Test
    public void testASigninPromptSuccess() {
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

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Sign-in Successful!"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt (e.g., clicking a button)
        Espresso.onView(ViewMatchers.withText("PROCEED TO TIMER"))
                .perform(ViewActions.click());

        // Verify that the intended activity is launched (replace with your actual view matcher)
        Espresso.onView(ViewMatchers.withId(R.id.timer))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for sign-in prompt with invalid credentials.
     *
     * This test simulates a successful user registration scenario, performs the sign-in action
     * with invalid credentials, and verifies that the error prompt for invalid credentials
     * is displayed.
     */
    @Test
    public void testBSigninPromptFailNonExist() {
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
                .perform(ViewActions.typeText("testuser_non-existent"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_signin))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.signin))
                .perform(ViewActions.click());

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Invalid credentials."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for sign-in prompt with blank username field.
     *
     * This test simulates a successful registration scenario, performs the sign-in action with a
     * blank username field, and verifies that the error prompt for missing fields is displayed.
     */
    @Test
    public void testCSigninPromptBlankID() {
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
                .perform(ViewActions.typeText(""));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_signin))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.signin))
                .perform(ViewActions.click());

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Fill all the fields."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for sign-in prompt with blank password field.
     *
     * This test simulates a successful registration scenario, performs the sign-in action with
     * a blank password field, and verifies that the error prompt for missing fields is displayed.
     */
    @Test
    public void testDSigninPromptBlankPassword() {
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
                .perform(ViewActions.typeText("testuser_non-existent"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_signin))
                .perform(ViewActions.typeText(""));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.signin))
                .perform(ViewActions.click());

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Fill all the fields."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}

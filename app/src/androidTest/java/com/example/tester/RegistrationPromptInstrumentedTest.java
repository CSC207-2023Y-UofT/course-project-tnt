package com.example.tester;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.tester.UI.RegisterUser;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * This class contains instrumented UI tests for the prompt functionality tied to registration
 * features.
 *
 * It tests different scenarios of registration attempts including successful registration,
 * registration with sign-in with invalid credentials, and registration with missing fields
 * using the Espresso testing framework.
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
public class RegistrationPromptInstrumentedTest {
    /**
     * Rule to launch the RegisterUser activity for testing
     */
    @Rule
    public ActivityScenarioRule<RegisterUser> activityRule =
            new ActivityScenarioRule<>(RegisterUser.class);

    /**
     * Test case for successful registration prompt.
     *
     * This test simulates a successful registration scenario, performs the registration action
     * with valid credentials, and verifies that the prompt for successful registration is
     * displayed. Then, it verifies that the "SIGN-IN" button sends the user to the sign-in page.
     */
    @Test
    public void testARegistrationPromptSuccess() {
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

        // Verify that the success prompt is displayed
        Espresso.onView(ViewMatchers.withText("Registration Successful."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("SIGN-IN"))
                .perform(ViewActions.click());

        // Verify that the unique view element in the SigninUser activity is displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_signin))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    /**
     * Test case for prompt for registration failure due to existing user.
     *
     * This test simulates a registration scenario where the user already exists, performs the
     * registration action, and verifies that the prompt for existing user is displayed.
     * Then, it verifies that the "SIGN-IN" button sends the user to the sign-in page.
     */
    @Test
    public void testBRegistrationPromptFailExistsAndSignIn() {
        // Simulate a registration failure scenario
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

        // Verify that the failure prompt is displayed
        Espresso.onView(ViewMatchers.withText("User already exists. \n Please Sign In."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("SIGN-IN"))
                .perform(ViewActions.click());

        // Verify that the registration fields are still displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_signin))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password_signin))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for prompt for registration failure due to incorrect password.
     *
     * This test simulates a registration scenario with incorrect passwords, performs the
     * registration action, and verifies that the prompt for password mismatch is displayed.
     * Then, it verifies that the "TRY AGAIN" button allows the user to remain on the registration
     * page.
     */
    @Test
    public void testCRegistrationPromptFailMismatch() {
        // Simulate a registration failure scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .perform(ViewActions.typeText("testusernew"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.retype_password))
                .perform(ViewActions.typeText("mismatchpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.register))
                .perform(ViewActions.click());

        // Verify that the failure prompt is displayed
        Espresso.onView(ViewMatchers.withText("Password not Matching."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("TRY AGAIN"))
                .perform(ViewActions.click());

        // Verify that the registration fields are still displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for prompt for registration failure due to blank username field.
     *
     * This test simulates a registration scenario with blank username field, performs the
     * registration action, and verifies that the prompt for missing fields is displayed.
     * Then, it verifies that the "TRY AGAIN" button allows the user to remain on the registration
     * page.
     */
    @Test
    public void testDRegistrationPromptFailBlankID() {
        // Simulate a registration failure scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .perform(ViewActions.typeText(""));

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

        // Verify that the failure prompt is displayed
        Espresso.onView(ViewMatchers.withText("Fill all the fields."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("TRY AGAIN"))
                .perform(ViewActions.click());

        // Verify that the registration fields are still displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for prompt for registration failure due to blank password field.
     *
     * This test simulates a registration scenario with blank password field, performs the
     * registration action, and verifies that the prompt for missing fields is displayed.
     * Then, it verifies that the "TRY AGAIN" button allows the user to remain on the registration
     * page.
     */
    @Test
    public void testERegistrationPromptFailBlankPassword() {
        // Simulate a registration failure scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .perform(ViewActions.typeText("testuser"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .perform(ViewActions.typeText(""));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.retype_password))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.register))
                .perform(ViewActions.click());

        // Verify that the failure prompt is displayed
        Espresso.onView(ViewMatchers.withText("Fill all the fields."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("TRY AGAIN"))
                .perform(ViewActions.click());

        // Verify that the registration fields are still displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test case for prompt for registration failure due to blank retype password field.
     *
     * This test simulates a registration scenario with blank retype password field, performs the
     * registration action, and verifies that the prompt for missing fields is displayed.
     * Then, it verifies that the "TRY AGAIN" button allows the user to remain on the registration
     * page.
     */
    @Test
    public void testFRegistrationPromptFailBlankRePassword() {
        // Simulate a registration failure scenario
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .perform(ViewActions.typeText("testuser"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .perform(ViewActions.typeText("testpassword"));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.retype_password))
                .perform(ViewActions.typeText(""));

        // Close the soft keyboard
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.register))
                .perform(ViewActions.click());

        // Verify that the failure prompt is displayed
        Espresso.onView(ViewMatchers.withText("Fill all the fields."))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Perform action in the prompt
        Espresso.onView(ViewMatchers.withText("TRY AGAIN"))
                .perform(ViewActions.click());

        // Verify that the registration fields are still displayed
        Espresso.onView(ViewMatchers.withId(R.id.username_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password_register))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}

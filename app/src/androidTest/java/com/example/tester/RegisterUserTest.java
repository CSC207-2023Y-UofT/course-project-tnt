package com.example.tester;

import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.tester.RegisterUser;
import com.example.tester.DatabaseHelper;
import com.example.tester.HomePageUI;
import com.example.tester.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterUserTest {

    @Rule
    public ActivityScenarioRule<RegisterUser> activityScenarioRule =
            new ActivityScenarioRule<>(RegisterUser.class);

    private DatabaseHelper databaseHelper;

    @Before
    public void setup() {
        // Before running each test, create an instance of the DatabaseHelper.
        databaseHelper = new DatabaseHelper(activityScenarioRule.getScenario().getApplicationContext());
    }

    @After
    public void tearDown() {
        // After running each test, close the database to clean up.
        databaseHelper.close();
    }

    @Test
    public void testRegisterUserSuccess() {
        // Test successful user registration.

        // Enter valid registration details
        Espresso.onView(ViewMatchers.withId(R.id.username_register)).perform(ViewActions.typeText("testuser"));
        Espresso.onView(ViewMatchers.withId(R.id.password_register)).perform(ViewActions.typeText("testpassword"));
        Espresso.onView(ViewMatchers.withId(R.id.retype_password)).perform(ViewActions.typeText("testpassword"));

        // Click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.register)).perform(ViewActions.click());

        // Check if the registration success Toast is displayed
        Espresso.onView(withText(R.string.registration_success))
                .inRoot(withDecorView(not(activityScenarioRule.getScenario().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        // Check if the user is navigated to the HomePageUI activity
        activityScenarioRule.getScenario().onActivity(activity -> {
            assertTrue(activity instanceof HomePageUI);
        });
    }

    @Test
    public void testRegisterUserFailure() {
        // Test unsuccessful user registration (when username already exists).

        // Add a user to the database with the same username as the one we will try to register.
        databaseHelper.addUser(databaseHelper, "existinguser", "existingpassword");

        // Enter registration details with an existing username
        Espresso.onView(ViewMatchers.withId(R.id.username_register)).perform(ViewActions.typeText("existinguser"));
        Espresso.onView(ViewMatchers.withId(R.id.password_register)).perform(ViewActions.typeText("newpassword"));
        Espresso.onView(ViewMatchers.withId(R.id.retype_password)).perform(ViewActions.typeText("newpassword"));

        // Click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.register)).perform(ViewActions.click());

        // Check if the "User already exists" Toast is displayed
        Espresso.onView(withText(R.string.user_exists))
                .inRoot(withDecorView(not(activityScenarioRule.getScenario().getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        // Check if the user remains on the RegisterUser activity
        activityScenarioRule.getScenario().onActivity(activity -> {
            assertTrue(activity instanceof RegisterUser);
        });
    }
}

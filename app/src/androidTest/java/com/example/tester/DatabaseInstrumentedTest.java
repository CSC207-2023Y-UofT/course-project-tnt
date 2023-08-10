package com.example.tester;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.tester.Utils.DatabaseHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This class contains instrumentation tests for the database operations in the DatabaseHelper class.
 * It utilizes AndroidJUnit4 and the ActivityScenarioRule to perform tests on the database.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {
    private DatabaseHelper testDb;

    /**
     * Rule that launches the MainActivity before each test and manages its lifecycle.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Sets up the test environment by initializing the database helper.
     */
    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        testDb = new DatabaseHelper(context);
    }

    /**
     * Tests user addition, username existence check, and password validation.
     * It asserts that the operations are performed successfully by checking the expected results.
     */
    @Test
    public void testDatabaseOperations() {
        // Adding a user and checking if the username exists
        testDb.addUser(testDb, "testuser", "testpassword");
        assertTrue(testDb.checkUsername("testuser"));
        // Checking if the password for the existing user is valid
        assertTrue(testDb.checkPassword("testuser", "testpassword"));
    }
}


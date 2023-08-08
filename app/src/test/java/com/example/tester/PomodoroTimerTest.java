package com.example.tester;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PomodoroTimerTest {

    private FragmentScenario<PomodoroTimer> fragmentScenario;

    @Before
    public void setUp() {
        // Initialize FragmentScenario
        fragmentScenario = FragmentScenario.launchInContainer(PomodoroTimer.class);
    }

    @Test
    public void testTimerStartPauseButton() {
        // Verify the initial state of the timer
        onView(withId(R.id.startPauseButton)).perform(click());
        assertTrue(fragmentScenario.getFragment().isTimerRunning);

        // Pause the timer and verify the state
        onView(withId(R.id.startPauseButton)).perform(click());
        assertFalse(fragmentScenario.getFragment().isTimerRunning);

        // Resume the timer and verify the state
        onView(withId(R.id.startPauseButton)).perform(click());
        assertTrue(fragmentScenario.getFragment().isTimerRunning);
    }

    @Test
    public void testTimerResetButton() {
        // Start the timer
        onView(withId(R.id.startPauseButton)).perform(click());
        assertTrue(fragmentScenario.getFragment().isTimerRunning);

        // Reset the timer and verify the state
        onView(withId(R.id.resetButton)).perform(click());
        assertFalse(fragmentScenario.getFragment().isTimerRunning);
    }

}

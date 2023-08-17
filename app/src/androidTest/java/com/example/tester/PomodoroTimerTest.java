package com.example.tester;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import android.os.CountDownTimer;
import android.view.View;

import com.example.tester.UI.PomodoroTimer;
import com.example.tester.util.VibratorHelper;

/**
 * Unit tests for the {@link PomodoroTimer} class.
 * This class tests the functions and methods in PomodoroTimer.
 */

public class PomodoroTimerTest {

    @Mock
    private VibratorHelper mockVibratorHelper;

    private PomodoroTimer pomodoroTimer;

    /**
     * Sets up the testing environment before each test method is executed.
     * Initializes the PomodoroTimer instance and mocks dependencies.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pomodoroTimer = new PomodoroTimer();
        pomodoroTimer.vibratorHelper = mockVibratorHelper;
    }

    /**
     * Tests the behavior of starting the timer with work duration.
     * Verifies that the vibrator is invoked when the timer finishes.
     */
    @Test
    public void testStartTimer_WorkDuration() {
        long workDuration = PomodoroTimer.WORK_DURATION;
        pomodoroTimer.startTimer(workDuration);
        // Verify that the vibrator is called when the timer finishes
        verify(mockVibratorHelper).vibrate(1000);
    }

    /**
     * Tests the behavior of starting the timer with break duration.
     * Verifies that the vibrator is invoked when the timer finishes.
     */
    @Test
    public void testStartTimer_BreakDuration() {
        long breakDuration = PomodoroTimer.BREAK_DURATION;
        pomodoroTimer.startTimer(breakDuration);
        // Verify that the vibrator is called when the timer finishes
        verify(mockVibratorHelper).vibrate(1000);
    }

    /**
     * Tests the behavior of pausing the timer.
     * Verifies that the timer is canceled and UI elements are updated.
     */
    @Test
    public void testPauseTimer() {
        // Mock a running timer
        CountDownTimer mockTimer = mock(CountDownTimer.class);
        pomodoroTimer.timer = mockTimer;
        pomodoroTimer.isTimerRunning = true;

        pomodoroTimer.pauseTimer();

        // Verify that the timer is canceled and UI elements are updated
        verify(mockTimer).cancel();
        // Add assertions for UI element states if needed
    }

    /**
     * Tests the behavior of updating the timer text.
     * Verifies that the timer text view is updated correctly.
     */
    @Test
    public void testUpdateTimerText() {
        long millisUntilFinished = 150000; // 2 minutes and 30 seconds
        String expectedTimeText = "02:30";

        pomodoroTimer.updateTimerText(millisUntilFinished);

        // Verify that the timer text view is updated with the expected value
        assertEquals(expectedTimeText, pomodoroTimer.timerTextView.getText());
    }

    /**
     * Tests the behavior of resetting the timer.
     * Verifies that the timer is canceled and UI elements are updated.
     */
    @Test
    public void testResetTimer() {
        // Arrange
        long initialTime = 15000; // 15 seconds
        pomodoroTimer.timeLeftInMillis = initialTime;
        // Simulate a running timer
        pomodoroTimer.timer = new CountDownTimer(initialTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Do nothing
            }
            @Override
            public void onFinish() {
                // Do nothing
            }
        };
        pomodoroTimer.isTimerRunning = true;
        pomodoroTimer.startPauseButton.setText("Pause");
        pomodoroTimer.resetButton.setVisibility(View.GONE);

        // Act
        pomodoroTimer.resetTimer();

        // Assert
        verify(pomodoroTimer.timer).cancel();
        assertEquals(PomodoroTimer.WORK_DURATION, pomodoroTimer.timeLeftInMillis);
        verify(mockVibratorHelper, times(0)).vibrate(anyLong());
        assertFalse(pomodoroTimer.isTimerRunning);
        assertEquals("Start", pomodoroTimer.startPauseButton.getText());
        assertEquals(View.VISIBLE, pomodoroTimer.resetButton.getVisibility());
    }
}

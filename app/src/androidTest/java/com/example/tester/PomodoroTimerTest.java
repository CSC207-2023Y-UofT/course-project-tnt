package com.example.tester;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import com.example.tester.PomodoroTimer;
import com.example.tester.VibratorHelper;

@RunWith(AndroidJUnit4.class)
public class PomodoroTimerTest {

    private PomodoroTimer fragment;
    private Context contextMock;
    private VibratorHelper vibratorHelperMock;
    private FragmentManager fragmentManagerMock;

    @Before
    public void setup() {
        fragment = new PomodoroTimer();
        contextMock = mock(Context.class);
        vibratorHelperMock = mock(VibratorHelper.class);
        fragmentManagerMock = mock(FragmentManager.class);

        // Set up mocks
        when(contextMock.getSystemService(Context.VIBRATOR_SERVICE)).thenReturn(vibratorHelperMock);
        when(fragment.requireContext()).thenReturn(contextMock);
        fragment.vibratorHelper = vibratorHelperMock;
        fragment.TimerPrompt = new CustomPrompt(fragment.getContext(), "Test Title", "Test Message", "Button 1", "Button 2") {
            @Override
            public void onButton1Clicked() {
                // Mock implementation
            }
            @Override
            public void onButton2Clicked() {
                // Mock implementation
            }
        };
    }

    @Test
    public void testStartPauseButton_Click_StartsTimer() {
        fragment.isTimerRunning = false;
        fragment.timeLeftInMillis = 0;

        fragment.startPauseButton.performClick();

        // Verify that timer starts, UI updates, and button text changes
    }

    @Test
    public void testStartPauseButton_Click_PausesTimer() {
        fragment.isTimerRunning = true;
        fragment.timeLeftInMillis = 10000; // Set some value

        fragment.startPauseButton.performClick();

        // Verify that timer is paused, UI updates, and button text changes
    }

    @Test
    public void testResetButton_Click_ResetsTimer() {
        fragment.timeLeftInMillis = 20000; // Set some value

        fragment.resetButton.performClick();

        // Verify that timer is reset, UI updates, and button visibility changes
    }

    @Test
    public void testOnFinish_StartsBreakOrPrompt() {
        long workDuration = PomodoroTimer.WORK_DURATION;
        long breakDuration = PomodoroTimer.BREAK_DURATION;

    }

}


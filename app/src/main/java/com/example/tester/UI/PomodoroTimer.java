package com.example.tester.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tester.R;
import com.example.tester.util.VibratorHelper;

/**
 * A fragment class that implements the Pomodoro Timer functionality.
 * This class provides a timer for work and break intervals using the Pomodoro Technique.
 */
public class PomodoroTimer extends Fragment {
    /** The TextView displaying the timer. */
    public TextView timerTextView;

    /** The button to start or pause the timer. */
    public Button startPauseButton;

    /** The button to reset the timer. */
    public Button resetButton;

    /** The CountDownTimer instance for managing the timer. */
    public CountDownTimer timer;

    /** Indicates whether the timer is currently running. */
    public boolean isTimerRunning;

    /** The remaining time in milliseconds. */
    public long timeLeftInMillis;

    /** The duration of a work session in milliseconds. */
    public static final long WORK_DURATION = 25 * 60 * 1000;

    /** The duration of a break session in milliseconds. */
    public static final long BREAK_DURATION = 5 * 60 * 1000;

    /** The helper class for handling vibration. */
    public VibratorHelper vibratorHelper;

    /** Indicates if the prompt should be shown */
    public boolean showPromptOnBreakFinish = false;

    /** A text view displaying whether it is a work timer or a break timer */
    public TextView sessionTypeTextView;

    /**
     * Initializes the UI elements and sets up button click listeners. */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);
        timerTextView = rootView.findViewById(R.id.timerTextView);
        startPauseButton = rootView.findViewById(R.id.startPauseButton);
        resetButton = rootView.findViewById(R.id.resetButton);
        sessionTypeTextView = rootView.findViewById(R.id.sessionTypeTextView);

        startPauseButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer(timeLeftInMillis > 0 ? timeLeftInMillis : WORK_DURATION);
            }
        });
        resetButton.setOnClickListener(v -> resetTimer());

        vibratorHelper = new VibratorHelper(requireContext());

        return rootView;
    }

    /**
     * Starts the timer with the specified duration.
     * If the timer finishes, it triggers vibration and optionally shows a prompt.
     * The custom prompt to display when the timer prompts the user.
     * The prompt provides options to continue working or reset the timer.
     */
    public void startTimer(long duration) {
        Prompt TimerPrompt = new CustomPrompt(
                getContext(),
                "Good Job!",
                "Do you want to continue working?",
                "YES (START TIMER)",
                "NO (RESET TIMER)"
        ) {
            @Override
            public void onButton1Clicked() {
                startTimer(WORK_DURATION);
            }

            @Override
            public void onButton2Clicked() {
                resetTimer();
            }
        };
        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText(millisUntilFinished);
            }
            @Override
            public void onFinish() {
                vibratorHelper.vibrate(1000);
                if (duration == WORK_DURATION) {
                    startTimer(BREAK_DURATION);
                } else {
                    TimerPrompt.show();
                }
                sessionTypeTextView.setText(duration == WORK_DURATION ? "Keep working" : "Take a break");
            }
        }.start();

        isTimerRunning = true;
        startPauseButton.setText("Pause");
        resetButton.setVisibility(View.GONE);

        if (duration == WORK_DURATION) {
            showPromptOnBreakFinish = true;
        } else {
            showPromptOnBreakFinish = false;
        }

        sessionTypeTextView.setText(showPromptOnBreakFinish ? getString(R.string.session_type_break) : getString(R.string.session_type_work));
    }

    /**
     * Pauses the timer and updates the UI accordingly.
     */
    public void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        startPauseButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }

    /**
     * Resets the timer to the default work duration and updates the UI accordingly.
     */
    public void resetTimer() {
        timer.cancel();
        timeLeftInMillis = WORK_DURATION;
        updateTimerText(WORK_DURATION);

        startPauseButton.setText("Start");
        startPauseButton.setVisibility(View.VISIBLE);
        resetButton.setVisibility(View.GONE);

        isTimerRunning = false;

        sessionTypeTextView.setText(isTimerRunning ? "Take a break" : "Keep working");
    }

    /**
     * Updates the current countdown time and updates the UI accordingly.
     */
    public void updateTimerText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        @SuppressLint("DefaultLocale") String timeLeft = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeft);


        // Update the sessionTypeTextView based on the timer state
        sessionTypeTextView.setText(showPromptOnBreakFinish ? "Keep working" : "Take a break");
    }

    /**
     * Handles the onStop event of the fragment.
     * Cancels the timer to prevent leaks when the fragment is stopped.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }
}



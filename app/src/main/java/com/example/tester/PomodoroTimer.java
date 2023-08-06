package com.example.tester;

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
public class PomodoroTimer extends Fragment {
    public TextView timerTextView;
    public Button startPauseButton;
    public Button resetButton;
    public CountDownTimer timer;
    public boolean isTimerRunning;
    public long timeLeftInMillis;
    public static final long WORK_DURATION = 25 * 60 * 1000;
    public static final long BREAK_DURATION = 5 * 60 * 1000;

    public VibratorHelper vibratorHelper;

    Prompt TimerPrompt = new CustomPrompt(
            getContext(),
            "Good Job!",
            "Do you want to continue working?",
            "YES (START TIMER)",
            "NO (RESET TIMER)"
    ) {
        @Override
        public void onButton1Clicked() {
            startTimer(timeLeftInMillis > 0 ? timeLeftInMillis : WORK_DURATION);
        }

        @Override
        public void onButton2Clicked() {
            resetTimer();
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);
        timerTextView = rootView.findViewById(R.id.timerTextView);
        startPauseButton = rootView.findViewById(R.id.startPauseButton);
        resetButton = rootView.findViewById(R.id.resetButton);
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

    private void startTimer(long duration) {
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
                    startTimer(WORK_DURATION);
                }
            }
        }.start();
        isTimerRunning = true;
        startPauseButton.setText("Pause");
        resetButton.setVisibility(View.GONE);
    }
    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        startPauseButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        timer.cancel();
        timeLeftInMillis = WORK_DURATION;
        updateTimerText(WORK_DURATION);
        startPauseButton.setText("Start");
        resetButton.setVisibility(View.GONE);
    }
    private void updateTimerText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        @SuppressLint("DefaultLocale") String timeLeft = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeft);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }
}
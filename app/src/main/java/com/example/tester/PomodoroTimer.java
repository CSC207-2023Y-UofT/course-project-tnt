package com.example.tester;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tester.R;

public class PomodoroTimer extends AppCompatActivity {

    private TextView timerTextView;
    private Button startPauseButton;
    private Button resetButton;
    private CountDownTimer timer;
    private boolean isTimerRunning;
    private long timeLeftInMillis;
    private static final long WORK_DURATION = 25 * 60 * 1000;
    private static final long BREAK_DURATION = 5 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timer);

        timerTextView = findViewById(R.id.timerTextView);
        startPauseButton = findViewById(R.id.startPauseButton);
        resetButton = findViewById(R.id.resetButton);

        startPauseButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer(timeLeftInMillis > 0 ? timeLeftInMillis : WORK_DURATION);
            }
        });

        resetButton.setOnClickListener(v -> resetTimer());
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
                if (duration == WORK_DURATION) {
                    updateTimerText(BREAK_DURATION);
                    startTimer(BREAK_DURATION);
                } else {
                    updateTimerText(WORK_DURATION);
                    resetButton.setVisibility(View.VISIBLE);
                    startPauseButton.setText("Start");
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
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
        }
    }
}

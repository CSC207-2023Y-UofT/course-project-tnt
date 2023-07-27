package pomodoro.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Timer extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton;
    private Button resetButton;

    private CountDownTimer timer;
    private boolean isTimerRunning = false;
    private long timeRemaining = 0;
    private final long WORK_DURATION = 25 * 60 * 1000;
    private final long BREAK_DURATION = 5 * 60 * 1000;
    private final long LONG_BREAK_DURATION = 15 * 60;
    private final int WORK_ROUNDS = 4;
    private int workRoundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_page);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    if (timeRemaining <= 0) {
                        startTimer(WORK_DURATION);
                    } else {
                        startTimer(timeRemaining);
                    }
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void startTimer(long duration) {
        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                if (isTimerRunning) {
                    if (workRoundCount < WORK_ROUNDS) {
                        workRoundCount++;
                        startTimer(BREAK_DURATION);
                    } else {
                        workRoundCount = 0;
                        startTimer(WORK_DURATION);
                    }
                } else {
                    startTimer(WORK_DURATION);
                }
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("PAUSE");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        timeRemaining = timeRemaining > 0 ? timeRemaining : WORK_DURATION;
        updateTimerText(timeRemaining);
        startButton.setText("START");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        isTimerRunning = false;
        timeRemaining = 0;
        updateTimerText(WORK_DURATION);
        startButton.setText("START");
        resetButton.setVisibility(View.INVISIBLE);
        workRoundCount = 0;
    }

    private void updateTimerText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
    }
}

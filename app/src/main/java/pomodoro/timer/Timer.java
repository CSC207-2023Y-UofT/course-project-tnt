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
    private long timeRemainingInMillis = 0;
    private final long WORK_DURATION = 25 * 60 * 1000; // 25 minutes in milliseconds
    private final long BREAK_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds

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
                    startTimer(timeRemainingInMillis > 0 ? timeRemainingInMillis : WORK_DURATION);
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

    private void startTimer(long durationInMillis) {
        timer = new CountDownTimer(durationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemainingInMillis = millisUntilFinished;
                updateTimerText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                // Handle timer completion and switch between work and break sessions
                if (isTimerRunning) {
                    // Timer just finished the work session, start the break
                    startTimer(BREAK_DURATION);
                } else {
                    // Timer just finished the break session, start the work
                    startTimer(WORK_DURATION);
                }
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("Pause");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        startButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        isTimerRunning = false;
        timeRemainingInMillis = 0;
        updateTimerText(WORK_DURATION);
        startButton.setText("Start");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void updateTimerText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        timerTextView.setText(String.format("%02d:%02d", minutes, seconds));
    }
}

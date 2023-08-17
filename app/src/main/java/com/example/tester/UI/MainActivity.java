package com.example.tester.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tester.R;
import com.google.android.material.button.MaterialButton;

/**
 * The main entry point of the application.
 * This activity displays buttons for navigating to the sign-in and registration screens.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting.
     * Sets up the UI and click listeners for navigation buttons.
     *
     * @param savedInstanceState A {@code Bundle} containing previously saved state information, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton signin_ui_btn = findViewById(R.id.signin_ui_btn);
        MaterialButton register_ui_btn = findViewById(R.id.register_ui_btn);
        signin_ui_btn.setOnClickListener(v -> {
            // Navigate to the SigninUser activity
            Intent intent = new Intent(MainActivity.this, SigninUser.class);
            startActivity(intent);
        });

        register_ui_btn.setOnClickListener(v -> {
            // Navigate to the RegisterUser activity
            Intent intent = new Intent(MainActivity.this, RegisterUser.class);
            startActivity(intent);
        });
    }
}

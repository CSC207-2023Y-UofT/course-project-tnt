package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

/**
 * The SigninUser class handles user sign-in functionality.
 * It allows users to input their credentials and attempts to sign them in.
 * Depending on the outcome, it displays prompts and navigates to appropriate screens.
 */
public class SigninUser extends AppCompatActivity {
    UserRepo userRepo = new DatabaseHelper(this);
    SigninValidation signinValidation= new SigninValidation();

    /**
     * Called when the activity is starting.
     * Initializes the UI and sets up the sign-in button click listener.
     *
     * @param savedInstanceState The saved state of the activity, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signin);

        TextView signinUsername = findViewById(R.id.username_signin);
        TextView signinPassword = findViewById(R.id.password_signin);
        MaterialButton signin_btn = findViewById(R.id.signin);

        signin_btn.setOnClickListener(v -> {
            final String username = String.valueOf(signinUsername.getText());
            final String password = String.valueOf(signinPassword.getText());

            try {
                signinValidation.validateSignin(userRepo, username, password);

                DatabaseHelper.getUsername = username;

                // Display success prompt and navigate to the timer screen
                Prompt SigninPromptSuccess = new CustomPrompt(
                        this,
                        "Success",
                        "Sign-in Successful!",
                        "PROCEED TO TIMER"
                ) {
                    @Override
                    public void onButton1Clicked() {
                        Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                        startActivity(intent);
                    }
                };
                SigninPromptSuccess.show();
            } catch (ValidationException e) {
                // Display error prompt and provide options to retry or register
                Prompt SigninPromptFail = new CustomPrompt(
                        this,
                        "Failed",
                        e.getMessage(),
                        "TRY AGAIN",
                        "REGISTER"
                ) {
                    @Override
                    public void onButton1Clicked() {
                    }

                    @Override
                    public void onButton2Clicked() {
                        // Opens the registration screen when the "REGISTER" button is clicked.
                        Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
                        startActivity(intent);
                    }
                };
                SigninPromptFail.show();
            }
        });
    }
}

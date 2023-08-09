package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

/**
 * The RegisterUser class handles user registration and UI interactions.
 * It extends AppCompatActivity to provide activity functionality.
 */
public class RegisterUser extends AppCompatActivity {

    /**
     * Initializes UI elements, handles registration validation, and prompts user with appropriate dialogs.
     *
     * @param savedInstanceState A Bundle containing the activity's previous state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        MaterialButton register_btn = findViewById(R.id.register);
        TextView registerUsername = findViewById(R.id.username_register);
        TextView registerPassword = findViewById(R.id.password_register);
        TextView retypePassword = findViewById(R.id.retype_password);
        DatabaseHelper myDB = new DatabaseHelper(this);

        register_btn.setOnClickListener(v -> {
            final String username = String.valueOf(registerUsername.getText());
            final String password = String.valueOf(registerPassword.getText());
            final String repassword = String.valueOf(retypePassword.getText());

            RegisterValidation registerUserValidation = new RegisterValidation();

            try {
                registerUserValidation.validateRegister(myDB, username, password, repassword);
                // Validate user registration inputs
                DatabaseHelper.getUsername = username;

                // Show a success prompt
                Prompt RegistrationPromptSuccess = new CustomPrompt(
                        this,
                        "Welcome!",
                        "Registration Successful.",
                        "SIGN-IN"
                ) {
                    @Override
                    public void onButton1Clicked() {
                        Intent intent = new Intent(getApplicationContext(), SigninUser.class);
                        startActivity(intent);
                    }
                };
                RegistrationPromptSuccess.show();

            } catch (RegistrationException e) {
                // Show a failure prompt with options to try again or sign in
                Prompt RegistrationPromptFail = new CustomPrompt(
                        this,
                        "Failed",
                        e.getMessage(),
                        "TRY AGAIN",
                        "SIGN-IN"
                ) {
                    @Override
                    public void onButton1Clicked() {
                        // Retry registration
                    }

                    @Override
                    public void onButton2Clicked() {
                        // Go to sign-in page
                        Intent intent = new Intent(getApplicationContext(), SigninUser.class);
                        startActivity(intent);
                    }
                };
                RegistrationPromptFail.show();
            }
        });
    }
}

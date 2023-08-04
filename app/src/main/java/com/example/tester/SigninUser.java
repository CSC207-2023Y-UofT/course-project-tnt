package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SigninUser extends AppCompatActivity {
    UserRepo userRepo = new DatabaseHelper(this);
    SigninValidation signinValidation= new SigninValidation();

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
                    Toast.makeText(SigninUser.this, "Sign-in Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                    startActivity(intent);
            } catch (ValidationException e) {
                Toast.makeText(SigninUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

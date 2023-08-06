package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterUser extends AppCompatActivity {

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
                Toast.makeText(RegisterUser.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                DatabaseHelper.getUsername = username;
            } catch (RegistrationException e) {
                Toast.makeText(RegisterUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

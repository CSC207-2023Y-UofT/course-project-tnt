package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SigninUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_signin);

        TextView signinUsername = findViewById(R.id.username_signin);
        TextView signinPassword = findViewById(R.id.password_signin);
        MaterialButton signin_btn = (MaterialButton) findViewById(R.id.signin);

        DatabaseHelper myDB = new DatabaseHelper(this);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = String.valueOf(signinUsername.getText());
                final String password = String.valueOf(signinPassword.getText());

                if (username.equals("") || password.equals("")){
                    Toast.makeText(SigninUser.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result = myDB.checkPassword(username, password);
                    if (result == true) {
                        Toast.makeText(SigninUser.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SigninUser.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                    }
                }
        }});

    }
}

package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
//import com.example.tester.

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        MaterialButton register_btn = (MaterialButton) findViewById(R.id.register);
        TextView registerUsername = findViewById(R.id.username_register);
        TextView registerPassword = findViewById(R.id.password_register);
        TextView retypePassword = findViewById(R.id.retype_password);
        DatabaseHelper myDB = new DatabaseHelper(this);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = String.valueOf(registerUsername.getText());
                final String password = String.valueOf(registerPassword.getText());
                final String repassword = String.valueOf(retypePassword.getText());

                if (username.equals("") || password.equals("") || repassword.equals("")) {
                    Toast.makeText(RegisterUser.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.equals(repassword)){
                        Boolean usercheckResult = myDB.checkUsername(username);
                        if (!usercheckResult) {
                            boolean regResult = myDB.addUser(myDB, username, password);
                            if (regResult){
                                Toast.makeText(RegisterUser.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                registerUsername.setText("");
                                registerPassword.setText("");
                                retypePassword.setText("");
                                Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterUser.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterUser.this, "User already exists. \n Please Sign In.", Toast.LENGTH_SHORT).show();
                        }
;                    }
                    else {
                        Toast.makeText(RegisterUser.this, "Password not Matching.", Toast.LENGTH_SHORT).show();
                    }
                }

            }});

    }
}
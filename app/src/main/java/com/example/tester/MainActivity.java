package com.example.tester;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton signinbtn = (MaterialButton) findViewById(R.id.signinbtn);
        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Open UserRegUI
            }
        });

    }
}
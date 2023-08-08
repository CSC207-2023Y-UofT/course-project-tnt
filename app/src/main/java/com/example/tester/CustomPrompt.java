package com.example.tester;

import android.content.Context;
import android.widget.Toast;

public class CustomPrompt extends Prompt {

    public CustomPrompt(Context context, String header, String dialog, String button1, String button2) {
        super(context, header, dialog, button1, button2);
    }

    public CustomPrompt(Context context, String header, String dialog, String button1) {
        super(context, header, dialog, button1, null);
    }

    @Override
    public void onButton1Clicked() {
        Toast.makeText(context, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButton2Clicked() {
        Toast.makeText(context, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
    }
}
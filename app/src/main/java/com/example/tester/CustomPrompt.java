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
        // Handle button1 click
        // For example: Perform an action or show a message
        // In this example, we'll just show a toast message
        Toast.makeText(context, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButton2Clicked() {
        // Handle button2 click (optional)
        // For example: Perform another action or show a message
        // In this example, we'll just show a toast message
        Toast.makeText(context, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
    }
}

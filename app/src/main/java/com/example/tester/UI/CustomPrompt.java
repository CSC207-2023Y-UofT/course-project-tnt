package com.example.tester.UI;

import android.content.Context;
import android.widget.Toast;

/**
 * The CustomPrompt class extends the Prompt class to create a custom prompt dialog with one or two
 * buttons and predefined actions for button clicks.
 *
 * This class provides constructors for different button configurations.
 */
public class CustomPrompt extends Prompt {

    /**
     * Creates a new instance of the CustomPrompt class with two buttons (button 1 and 2)
     *
     * @param context The application context.
     * @param header The header text to be displayed in the dialog.
     * @param dialog The main dialog text to be displayed in the dialog.
     * @param button1 The text for the primary button.
     * @param button2 The text for the secondary button.
     */
    public CustomPrompt(Context context, String header, String dialog,
                        String button1, String button2) {
        super(context, header, dialog, button1, button2);
    }

    /**
     * Creates a new instance of the CustomPrompt class with a single button (button 1 only).
     *
     * @param context The application context.
     * @param header The header text to be displayed in the dialog.
     * @param dialog The main dialog text to be displayed in the dialog.
     * @param button1 The text for the primary button.
     */
    public CustomPrompt(Context context, String header, String dialog, String button1) {
        super(context, header, dialog, button1, null);
    }

    /**
     * Called when the primary button (button 1) is clicked.
     * By default, it displays a short Android Toast indicating button 1 was clicked.
     */
    @Override
    public void onButton1Clicked() {
        Toast.makeText(context, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when the secondary button (button 2) is clicked.
     * By default, it displays a short Android Toast indicating button 2 was clicked.
     */
    @Override
    public void onButton2Clicked() {
        Toast.makeText(context, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
    }
}

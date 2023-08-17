package com.example.tester.UI;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * The Prompt abstract class provides a customizable prompt dialog with one or two buttons and
 * associated actions.
 *
 * Subclasses of this abstract class can override the onButton1Clicked() and onButton2Clicked()
 * methods to define the actions for the primary button (button 1) and the secondary button
 * (button 2).
 */
public abstract class Prompt {
    /**
     * The application context.
     */
    protected Context context;

    /**
     * The header text to be displayed in the dialog.
     */
    private String header;

    /**
     * The main dialog text to be displayed in the dialog.
     */
    private String dialog;

    /**
     * The text for the primary button.
     */
    private String button1;

    /**
     * The text for the secondary button (can be null).
     */
    private String button2;

    /**
     * Creates a new instance of the Prompt class.
     *
     * @param context The application context.
     * @param header The header text to be displayed in the dialog.
     * @param dialog The main dialog text to be displayed in the dialog.
     * @param button1 The text for the primary button.
     * @param button2 The text for the secondary button (can be null).
     */
    public Prompt(Context context, String header, String dialog, String button1,
                  @Nullable String button2) {
        this.context = context;
        this.header = header;
        this.dialog = dialog;
        this.button1 = button1;
        this.button2 = button2;
    }

    /**
     * This method is called when the primary button is clicked.
     * Subclasses should provide the desired behaviour for the primary button's action.
     */
    public abstract void onButton1Clicked();

    /**
     * This method is called when the secondary button is clicked.
     * Subclasses should provide the desired behaviour for the secondary button's action.
     */
    public void onButton2Clicked() {
    }

    /**
     * Displays the prompt dialog with the specified header, dialog text, and button texts using
     * Android AlertDialog.
     */
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(header)
                .setMessage(dialog)
                .setCancelable(false)
                .setPositiveButton(button1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onButton1Clicked();
                    }
                });

        if (button2 != null) {
            builder.setNegativeButton(button2, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    onButton2Clicked();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

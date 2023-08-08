package com.example.tester;

import android.content.Context;
import android.content.DialogInterface;


public abstract class Prompt {
    protected Context context;
    private String header;
    private String dialog;
    private String button1;
    private String button2;

    public Prompt(Context context, String header, String dialog, String button1, @Nullable String button2) {
        this.context = context;
        this.header = header;
        this.dialog = dialog;
        this.button1 = button1;
        this.button2 = button2;
    }

    public abstract void onButton1Clicked();

    public void onButton2Clicked() {
        // Optional method to be overridden if needed
    }

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
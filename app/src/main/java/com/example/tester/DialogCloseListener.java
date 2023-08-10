package com.example.tester;

import android.content.DialogInterface;

/**
 * An interface to handle the close event of a dialog.
 */
public interface DialogCloseListener {
    /**
     * Handles the event when a dialog is closed.
     *
     * @param dialog The dialog that was closed.
     */
    void handleDialogClose(DialogInterface dialog);
}

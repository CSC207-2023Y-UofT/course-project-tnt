package com.example.tester;

import android.content.DialogInterface;

/**
 * A listener interface to handle dialog close events.
 */
public interface DialogCloseListener {
    /**
     * Called when a dialog is dismissed or closed.
     *
     * @param dialog The DialogInterface of the dialog that was closed.
     */
    void handleDialogClose(DialogInterface dialog);
}

package com.example.goodfordaily.util.Helper;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.goodfordaily.R;

public class DialogHelper {

    private static AlertDialog.Builder alertDialogBuilder;
    private static AlertDialog alertDialog;

    public static void dialogShow(Context context, int style, String title, String message) {
        //Alert Dialog
        alertDialogBuilder = new AlertDialog.Builder(context, style);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(R.string.confirm, (dialogInterface, i) -> dialogDismiss());

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void dialogShow(Context context, int style, String title, String message, DialogInterface.OnClickListener positiveClickListener) {
        dialogShow(context, style, title, message, positiveClickListener, false);
    }

    public static void dialogShow(Context context, int style, String title, String message, DialogInterface.OnClickListener positiveClickListener, boolean negativeButtonShow) {
        //Alert Dialog
        alertDialogBuilder = new AlertDialog.Builder(context, style);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(R.string.confirm, positiveClickListener);

        if (negativeButtonShow) {
            alertDialogBuilder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogDismiss());
        }

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void dialogShow(Context context, int style, String title, String message, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener) {
        //Alert Dialog
        alertDialogBuilder = new AlertDialog.Builder(context, style);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton(R.string.confirm, positiveClickListener);
        alertDialogBuilder.setNegativeButton(R.string.cancel, negativeClickListener);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void dialogDismiss() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
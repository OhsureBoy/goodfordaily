package com.example.goodfordaily.util.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.goodfordaily.R;

import java.util.Objects;

public class CustomDialog extends DialogFragment {
    private String title;
    private String message;

    public CustomDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.dialog_message,null);

        dialog.setOnClickListener(view->{
            if(getDialog() != null) {
                getDialog().dismiss();
            }
        });

        TextView textView_title = dialog.findViewById(R.id.textView_title);
        TextView textView_message = dialog.findViewById(R.id.textView_message);

        textView_title.setText(title);
        textView_message.setText(message);

        builder.setView(dialog);

        AlertDialog alertDialog = builder.create();

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }
}

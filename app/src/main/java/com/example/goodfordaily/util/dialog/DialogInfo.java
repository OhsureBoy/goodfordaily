package com.example.goodfordaily.util.dialog;

import android.content.DialogInterface;

public class DialogInfo {

    private int style;
    private String title;
    private String message;
    private String button;
    private DialogInterface.OnClickListener mOnClickListener;

    public DialogInfo(int style, String title, String message) {
        this.style = style;
        this.title = title;
        this.message = message;
        this.button = null;
    }

    public DialogInfo(int style, String title, String message, String button) {
        this.style = style;
        this.title = title;
        this.message = message;
        this.button = button;
    }

    public DialogInfo(int style, String title, String message, String button, DialogInterface.OnClickListener mOnClickListener) {
        this.style = style;
        this.title = title;
        this.message = message;
        this.button = button;
        this.mOnClickListener = mOnClickListener;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public DialogInterface.OnClickListener getmOnClickListener() {
        return mOnClickListener;
    }

    public void setmOnClickListener(DialogInterface.OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}

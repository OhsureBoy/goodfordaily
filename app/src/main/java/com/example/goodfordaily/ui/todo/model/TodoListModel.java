package com.example.goodfordaily.ui.todo.model;

import android.widget.Button;

public class TodoListModel {

    private String text;
    private boolean clicked;
    private Button.OnClickListener onClickListener;

    public TodoListModel(String text, boolean clicked, Button.OnClickListener onClickListener) {
        this.text = text;
        this.clicked = clicked;
        this.onClickListener = onClickListener;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public Button.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(Button.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

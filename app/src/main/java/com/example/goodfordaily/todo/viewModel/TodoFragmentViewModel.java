package com.example.goodfordaily.todo.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TodoFragmentViewModel extends AndroidViewModel {

    public MutableLiveData<String> todoListData;
    public TodoFragmentViewModel(@NonNull Application application) {
        super(application);
        todoListData = new MutableLiveData<>();
    }

    public void addTodoList() {
        Log.e("TAG", "addTodoList: " + todoListData.getValue() );

        todoListData.setValue("");
    }
}

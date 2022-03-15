package com.example.goodfordaily.ui.todo.viewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.todoList.TodoRepository;
import com.example.goodfordaily.ui.menu.viewModel.MenuViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

public class TodoFragmentViewModel extends AndroidViewModel {

    public MutableLiveData<String> todoListData;
    public MutableLiveData<Boolean> checkBoxClicked;

    private TodoRepository todoRepository;
    private LiveData<List<TodoModel>> allNotes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String name;

    public TodoFragmentViewModel(@NonNull Application application) {
        super(application);
        todoListData = new MutableLiveData<>();
        checkBoxClicked = new MutableLiveData<>();

    }

    public String getName() {
        return this.name;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void setTodoRepository(Application application, String name) {
        this.name = name;
        todoRepository = new TodoRepository(application,compositeDisposable,name);
        allNotes = todoRepository.getAllTasks();
    }
    public void insert(TodoModel task) {
        todoRepository.insert(task);
    }

    public void delete(TodoModel task) {
        todoRepository.delete(task);
    }


    public LiveData<List<TodoModel>> getAllTasks() {
        return allNotes;
    }
}

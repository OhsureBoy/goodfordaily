package com.example.goodfordaily.ui.todo.viewModel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.todoList.TodoRepository;
import com.example.goodfordaily.ui.todo.model.TodoListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.disposables.CompositeDisposable;

public class TodoFragmentViewModel extends AndroidViewModel {

    public MutableLiveData<String> todoListData;
    public ArrayList<TodoListModel> todoList;
    public MutableLiveData<Boolean> checkBoxClicked;

    private TodoRepository todoRepository;
    private LiveData<List<TodoModel>> allNotes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public TodoFragmentViewModel(@NonNull Application application) {
        super(application);
        todoListData = new MutableLiveData<>();
        todoList = new ArrayList<>();
        checkBoxClicked = new MutableLiveData<>();

        todoRepository = new TodoRepository(application,compositeDisposable);
        allNotes = todoRepository.getAllTasks();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void addTodoList() {
        //여기서 DB에 값을 넣자구요
        if(!Objects.equals(Objects.requireNonNull(todoListData.getValue()).trim(),""))
            todoList.add(new TodoListModel( todoListData.getValue() , checkBoxClicked.getValue() , onClickListener));
    }

    Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("TAG", "onClick: " + "DELETE" );
        }
    };

    public void insert(TodoModel task) {
        todoRepository.insert(task);
    }

    public void update(TodoModel task) {
        todoRepository.update(task);
    }

    public void delete(TodoModel task) {
        todoRepository.delete(task);
    }

    public void deleteAllNotes() {
        todoRepository.deleteAllNote();
    }

    public LiveData<List<TodoModel>> getAllTasks() {
        return allNotes;
    }
}

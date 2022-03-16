package com.example.goodfordaily.ui.home.viewModel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.databinding.FragmentHomeBinding;
import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.diary.DiaryRepository;
import com.example.goodfordaily.repository.todoList.TodoRepository;
import com.example.goodfordaily.util.onBackPressedListener;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class HomeFragmentViewModel extends AndroidViewModel {
    private TodoRepository todoRepository;
    private LiveData<List<TodoModel>> todoAllNotes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String name;
    private DiaryRepository diaryRepository;
    private LiveData<List<DiaryModel>> diaryAllNotes;

    public MutableLiveData<Long> date;

    public HomeFragmentViewModel(@NonNull Application application) {

        super(application);
        date = new MutableLiveData<>();
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setDiaryRepository(Application application, String name) {
        this.name = name;
        diaryRepository = new DiaryRepository(application,compositeDisposable,name);
    }
    public void insert(DiaryModel task) {
        diaryRepository.insert(task);
    }

    public void delete(DiaryModel task) {
        diaryRepository.delete(task);
    }

    public LiveData<List<DiaryModel>> getDiaryAllTasks(String name, String date) {
        diaryAllNotes = diaryRepository.getDateTasks(name, date);
        return diaryAllNotes;
    }

    public LiveData<List<TodoModel>> getTodolistAllTasks(String name, String date) {
        todoAllNotes = todoRepository.getDateTasks(name, date);
        return todoAllNotes;
    }

    public void setTodoRepository(Application application, String name) {
        this.name = name;
        todoRepository = new TodoRepository(application,compositeDisposable,name);
        todoAllNotes = todoRepository.getAllTasks();
    }
    public void insert(TodoModel task) {
        todoRepository.insert(task);
    }


    public void delete(TodoModel task) {
        todoRepository.delete(task);
    }

}

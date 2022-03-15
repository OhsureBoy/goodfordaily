package com.example.goodfordaily.ui.diary.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.repository.diary.DiaryRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DiaryFragmentViewModel extends AndroidViewModel {

    private DiaryRepository diaryRepository;
    private LiveData<List<DiaryModel>> allNotes;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    String name;

    public DiaryFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTodoRepository(Application application, String name) {
        this.name = name;
        diaryRepository = new DiaryRepository(application,compositeDisposable,name);
        allNotes = diaryRepository.getAllTasks();
    }
    public void insert(DiaryModel task) {
        diaryRepository.insert(task);
    }

    public void update(DiaryModel task) {
        diaryRepository.update(task);
    }

    public void delete(DiaryModel task) {
        diaryRepository.delete(task);
    }

    public void deleteAllNotes() {
        diaryRepository.deleteAllNote();
    }

    public LiveData<List<DiaryModel>> getAllTasks() {
        return allNotes;
    }
}

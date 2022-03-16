package com.example.goodfordaily.repository.diary;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.login.LoginDao;
import com.example.goodfordaily.repository.todoList.TodoDao;
import com.example.goodfordaily.util.database.TodoDatabase;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class DiaryRepository {

    private static final String TAG = "TaskRepository";

    private DiaryDao diaryDao;
    private LoginDao loginDao;
    private LiveData<List<DiaryModel>> allTasks;
    private CompositeDisposable compositeDisposable;

    public DiaryRepository(Application application, CompositeDisposable compositeDisposable, String name) {
        this.compositeDisposable = compositeDisposable;
        TodoDatabase database = TodoDatabase.getInstance(application);
        diaryDao = database.diaryDao();
        loginDao = database.loginDao();
        allTasks = diaryDao.getAllTasks(name);
    }

    public void insert(final DiaryModel task) {
        compositeDisposable.add(
                diaryDao.insert(task)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: insert success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: insert ", e);
                            }
                        })
        );
    }

    public void update(DiaryModel task) {
        compositeDisposable.add(
                diaryDao.update(task)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: update success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: update ", e);
                            }
                        })
        );
    }

    public void delete(DiaryModel task) {
        compositeDisposable.add(
                diaryDao.delete(task)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: delete success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: delete ", e);
                            }
                        })
        );
    }

    public void deleteAllNote() {
        compositeDisposable.add(
                diaryDao.deleteAllTasks()
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.d(TAG, "onComplete: deleteAllNote success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: deleteAllNote ", e);
                            }
                        })
        );
    }

    public LiveData<List<DiaryModel>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<DiaryModel>> getDateTasks(String name, String date) {
        return diaryDao.getDateTasks(name, date);
    }
}

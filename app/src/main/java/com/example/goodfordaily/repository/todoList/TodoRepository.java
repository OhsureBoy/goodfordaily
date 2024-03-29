package com.example.goodfordaily.repository.todoList;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.repository.login.LoginDao;
import com.example.goodfordaily.util.database.TodoDatabase;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class TodoRepository {

    private static final String TAG = "TaskRepository";

    private TodoDao todoDao;
    private LoginDao loginDao;
    private LiveData<List<TodoModel>> allTasks;
    private CompositeDisposable compositeDisposable;

    public TodoRepository(Application application, CompositeDisposable compositeDisposable,String name) {
        this.compositeDisposable = compositeDisposable;
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoDao = database.todoDao();
        loginDao = database.loginDao();
        allTasks = todoDao.getAllTasks(name);
    }

    public void insert(final TodoModel task) {
        compositeDisposable.add(
                todoDao.insert(task)
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

    public void update(TodoModel task) {
        compositeDisposable.add(
                todoDao.update(task)
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

    public void delete(TodoModel task) {
        compositeDisposable.add(
                todoDao.delete(task)
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
                todoDao.deleteAllTasks()
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

    public LiveData<List<TodoModel>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<TodoModel>> getDateTasks(String name, String date) {
        return todoDao.getDateTasks(name, date);
    }
}
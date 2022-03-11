package com.example.goodfordaily.repository.login;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.util.database.TodoDatabase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginRepository {

    private LoginDao loginDao;
    private LiveData<List<LoginModel>> allData;
    private CompositeDisposable compositeDisposable;
    private Disposable backgroundtask;
    boolean check;

    public LoginRepository(Application application ,CompositeDisposable compositeDisposable) {

        TodoDatabase db = TodoDatabase.getInstance(application);
        loginDao = db.loginDao();
        allData = loginDao.getDetails();
        this.compositeDisposable = compositeDisposable;
    }

    public void deleteData() {
        loginDao.deleteAllData();
    }

    public boolean getUserCheck(String name, String passwd) {

        backgroundtask = Observable.fromCallable(() -> {
            boolean userCheck = loginDao.getUserCheck(name,passwd);
            return userCheck;
        }) .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .onErrorReturn(throwable -> false)
                .subscribe((userCheck) -> {
                    if(userCheck)
                        check = true;
                    else
                        check =  false;

                    backgroundtask.dispose();
                });

        return check;
    }

    public boolean getSameName(String name) {
        return loginDao.getSameName(name);
    }

    public LiveData<List<LoginModel>> getAllData() {
        return allData;
    }

    public void insertData(LoginModel data) {
        compositeDisposable.add(
                loginDao.insert(data)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.d("TAG", "onComplete: insert success");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("TAG", "onError: insert ", e);
                            }
                        })
        );
    }
}
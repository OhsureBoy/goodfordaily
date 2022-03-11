package com.example.goodfordaily.ui.login.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.goodfordaily.repository.login.LoginRepository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private CompositeDisposable compositeDisposable;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        compositeDisposable = new CompositeDisposable();
        repository = new LoginRepository(application , compositeDisposable);
    }

    public boolean checkedLogin(String name, String passwd) {
        return repository.getUserCheck(name, passwd);
    }
}

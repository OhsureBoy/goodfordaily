package com.example.goodfordaily.ui.login.viewModel;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.R;
import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.repository.login.LoginRepository;
import com.example.goodfordaily.util.SaveSharedPreferences;
import com.example.goodfordaily.util.dialog.DialogInfo;

import java.util.List;
import java.util.Objects;
import java.util.Observer;

import io.reactivex.disposables.CompositeDisposable;

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

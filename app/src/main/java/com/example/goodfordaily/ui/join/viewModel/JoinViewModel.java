package com.example.goodfordaily.ui.join.viewModel;

import android.app.Application;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.R;
import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.repository.login.LoginRepository;
import com.example.goodfordaily.util.dialog.DialogInfo;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class JoinViewModel extends AndroidViewModel {


    private LoginRepository repository;
    private LiveData<List<LoginModel>> getAllData;
    private CompositeDisposable compositeDisposable;

    public JoinViewModel(@NonNull Application application) {
        super(application);

        compositeDisposable = new CompositeDisposable();

        repository = new LoginRepository(application , compositeDisposable);
        getAllData = repository.getAllData();
    }


    public boolean checkedUserId(String name) {
        return repository.getSameName(name);
    }
    public void insert(LoginModel data) {
        repository.insertData(data);
    }

    public LiveData<List<LoginModel>> getGetAllData() {
        return getAllData;
    }
}

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

    public MutableLiveData<String> joinId;
    public MutableLiveData<String> joinPassword;
    public MutableLiveData<Boolean> joinOK;
    public MutableLiveData<DialogInfo> dialog;

    private LoginRepository repository;
    private LiveData<List<LoginModel>> getAllData;
    private CompositeDisposable compositeDisposable;

    public JoinViewModel(@NonNull Application application) {
        super(application);

        joinId          = new MutableLiveData<>();
        joinPassword    = new MutableLiveData<>();
        joinOK          = new MutableLiveData<>();
        dialog          = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();

        repository = new LoginRepository(application , compositeDisposable);
        getAllData = repository.getAllData();
    }

    public void userJoinBtn() {
        System.out.print(joinId.getValue());
        if (joinId.getValue() != null && joinPassword.getValue() != null) {

            insert(new LoginModel(joinId.getValue(),joinPassword.getValue()));
            dialog.setValue(new DialogInfo(R.style.Theme_AppCompat_Dialog,"성공","화원 가입을 축하드립니다", "확인" ,okButtonClickListener));

        }
    }
    private DialogInterface.OnClickListener okButtonClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            joinOK.setValue(true);
        }

    };


    public void insert(LoginModel data) {
        repository.insertData(data);
    }

    public LiveData<List<LoginModel>> getGetAllData() {
        return getAllData;
    }
}

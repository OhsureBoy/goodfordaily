package com.example.goodfordaily.login.viewModel;

import static com.example.goodfordaily.util.databass.Databases.CreateUserDB.USERID;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.R;
import com.example.goodfordaily.login.LoginActivity;
import com.example.goodfordaily.util.Helper.DbOpenHelper;
import com.example.goodfordaily.util.SaveSharedPreferences;
import com.example.goodfordaily.util.dto.DialogInfo;
import com.example.goodfordaily.util.dto.LoginDto;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginViewModel extends AndroidViewModel {

    public MutableLiveData<String> userId;
    public MutableLiveData<String> password;
    public MutableLiveData<Boolean> login;
    public MutableLiveData<Boolean> join;
    public MutableLiveData<DialogInfo> dialog;
    private boolean checkLogin;

    private DbOpenHelper dbOpenHelper;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        userId          = new MutableLiveData<>();
        password        = new MutableLiveData<>();
        login           = new MutableLiveData<>();
        join            = new MutableLiveData<>();
        dbOpenHelper    = new DbOpenHelper(getApplication().getApplicationContext());
        dialog          = new MutableLiveData<>();
        dbOpenHelper.open();

    }

    public void loginBtnClick() {
        Cursor iCursor = dbOpenHelper.sortColumn(USERID);
        while(iCursor.moveToNext()){
            String tempID = iCursor.getString(iCursor.getColumnIndex("userId"));
            String tempPasswd = iCursor.getString(iCursor.getColumnIndex("password"));
            if(tempID.equals(userId.getValue()) && tempPasswd.equals(password.getValue())) {
                SaveSharedPreferences.setUserName(getApplication(),userId.getValue());
                checkLogin = true;
            }
        }
        if (checkLogin) {
            login.setValue(true);
        } else {
            login.setValue(false);
            dialog.setValue(new DialogInfo(R.style.Theme_AppCompat_Dialog,"실패","정보가 일치하지 않습니다."));
        }
    }

    public void joinBtnClick(){
        join.setValue(true);
    }
}

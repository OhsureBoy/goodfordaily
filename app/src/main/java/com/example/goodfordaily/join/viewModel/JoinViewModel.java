package com.example.goodfordaily.join.viewModel;

import android.app.Application;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.R;
import com.example.goodfordaily.generated.callback.OnClickListener;
import com.example.goodfordaily.util.Helper.DbOpenHelper;
import com.example.goodfordaily.util.dialog.CustomDialog;
import com.example.goodfordaily.util.dto.DialogInfo;
import com.example.goodfordaily.util.dto.LoginDto;

import java.util.Objects;

public class JoinViewModel extends AndroidViewModel {

    public MutableLiveData<String> joinId;
    public MutableLiveData<String> joinPassword;
    public MutableLiveData<Boolean> joinOK;
    public MutableLiveData<DialogInfo> dialog;

    private DbOpenHelper dbOpenHelper;

    public JoinViewModel(@NonNull Application application) {
        super(application);

        joinId          = new MutableLiveData<>();
        joinPassword    = new MutableLiveData<>();
        joinOK          = new MutableLiveData<>();
        dialog          = new MutableLiveData<>();

        dbOpenHelper = new DbOpenHelper(getApplication().getApplicationContext());
        dbOpenHelper.open();
        dbOpenHelper.Create();
    }

    public void userJoinBtn() {
        System.out.print(joinId.getValue());
        if (joinId.getValue() != null && joinPassword.getValue() != null) {
            dbOpenHelper.open();
            dbOpenHelper.insertColumn(joinId.getValue(), joinPassword.getValue());
            dialog.setValue(new DialogInfo(R.style.Theme_AppCompat_Dialog,"성공","화원 가입을 축하드립니다", "확인" ,okButtonClickListener));

        }
    }
    private DialogInterface.OnClickListener okButtonClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            joinOK.setValue(true);
        }

    };
}

package com.example.goodfordaily.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityLoginBinding;

import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.ui.join.JoinActivity;
import com.example.goodfordaily.ui.login.viewModel.LoginViewModel;

import com.example.goodfordaily.ui.menu.MenuActivity;
import com.example.goodfordaily.util.dialog.DialogHelper;
import com.example.goodfordaily.util.dialog.DialogInfo;

import java.util.Objects;
;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    Disposable backgroundtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLifecycleOwner(this);

        loginViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);

        loginViewModel.dialog.observe(this,dialog-> {
            if (dialog == null) {
                return;
            }
            if (dialog.getButton() == null) {
                DialogHelper.dialogShow(this, dialog.getStyle(), dialog.getTitle(), dialog.getMessage());
            } else {
                DialogHelper.dialogShow(this, dialog.getStyle(), dialog.getTitle(), dialog.getMessage(), dialog.getmOnClickListener());

            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backgroundtask = Observable.fromCallable(() -> {
                    boolean checkedLogin = loginViewModel.checkedLogin(binding.userId.getText().toString(), binding.userPassword.getText().toString());
                    return checkedLogin;
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(throwable -> false)
                        .subscribe((checkedLogin) -> {
                           if(checkedLogin) {
                               startActivity(new Intent(binding.getRoot().getContext(), MenuActivity.class));
                           }else {
                               DialogInfo dialog = new DialogInfo(R.style.Theme_AppCompat_Dialog, "실패", "아이디 패스워드를 확인하세요", "확인", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       dialog.dismiss();
                                   }
                               });
                               DialogHelper.dialogShow(binding.getRoot().getContext(), dialog.getStyle(), dialog.getTitle(), dialog.getMessage());
                           }
                            backgroundtask.dispose();
                        });
            }
        });

        binding.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    // 키보드 버튼 내리기 부분
    // InputMethodManager는 soft키보드를 관리한다.
    //View view getCurrentFocus는 현재 포커스를 가지고 온다.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert view != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
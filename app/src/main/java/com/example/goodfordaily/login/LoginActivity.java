package com.example.goodfordaily.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityLoginBinding;
import com.example.goodfordaily.join.JoinActivity;
import com.example.goodfordaily.login.viewModel.LoginViewModel;
import com.example.goodfordaily.menu.MenuActivity;
import com.example.goodfordaily.util.Helper.DialogHelper;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLifecycleOwner(this);

        loginViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);


        //회원가입 화면 이동
        loginViewModel.join.observe(this,JoinSuccess->{
            if(JoinSuccess) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //메뉴 화면 이동
        loginViewModel.login.observe(this,LoginSuccess -> {
            if(LoginSuccess) {
                startActivity(new Intent(this, MenuActivity.class));
            }
        });

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
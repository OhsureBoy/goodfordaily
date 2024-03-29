package com.example.goodfordaily.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLifecycleOwner(this);

        loginViewModel =  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkedLogin = loginViewModel.checkedLogin(binding.userId.getText().toString().trim(), binding.userPassword.getText().toString().trim());
                Log.e("TAG", "onClick: " + checkedLogin );
                if(checkedLogin) {
                    Intent intent = new Intent(binding.getRoot().getContext(), MenuActivity.class);
                    intent.putExtra("userName",binding.userId.getText().toString().trim());
                    startActivity(intent);
                }else {
                    DialogInfo dialog = new DialogInfo(R.style.Theme_AppCompat_Dialog, "실패", "아이디 패스워드를 확인하세요", "확인");
                    DialogHelper.dialogShow(binding.getRoot().getContext(), dialog.getStyle(), dialog.getTitle(), dialog.getMessage(), new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       dialog.dismiss();
                                   }
                               });
                }
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
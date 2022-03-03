package com.example.goodfordaily.join;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityJoinBinding;
import com.example.goodfordaily.join.viewModel.JoinViewModel;
import com.example.goodfordaily.login.LoginActivity;
import com.example.goodfordaily.util.Helper.DialogHelper;
import com.example.goodfordaily.util.dialog.CustomDialog;

public class JoinActivity extends AppCompatActivity {

    ActivityJoinBinding binding;
    JoinViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_join);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(JoinViewModel.class);

        binding.setViewModel(viewModel);

        viewModel.joinOK.observe(this,joinSuccess->{
            if(joinSuccess){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        viewModel.dialog.observe(this,dialog-> {
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
package com.example.goodfordaily.ui.join;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityJoinBinding;

import com.example.goodfordaily.model.LoginModel;
import com.example.goodfordaily.ui.join.viewModel.JoinViewModel;
import com.example.goodfordaily.ui.login.LoginActivity;
import com.example.goodfordaily.util.dialog.DialogHelper;
import com.example.goodfordaily.util.dialog.DialogInfo;
import io.reactivex.rxjava3.disposables.Disposable;

public class JoinActivity extends AppCompatActivity {

    ActivityJoinBinding binding;
    JoinViewModel viewModel;
    Disposable backgroundtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_join);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(JoinViewModel.class);

        binding.setViewModel(viewModel);

        binding.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkedUserId = viewModel.checkedUserId(binding.userId.getText().toString());
                if(checkedUserId){
                    DialogInfo dialog = new DialogInfo(R.style.Theme_AppCompat_Dialog, "실패", "이미 등록된 아이디 입니다.", "확인");
                                DialogHelper.dialogShow(binding.getRoot().getContext(), dialog.getStyle(), dialog.getTitle(), dialog.getMessage() , new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                } else {
                    viewModel.insert(new LoginModel(binding.userId.getText().toString().trim(), binding.userPassword.getText().toString().trim()));

                                    DialogInfo dialog = new DialogInfo(R.style.Theme_AppCompat_Dialog, "성공", "회원 가입을 축하합니다.", "확인");
                                    DialogHelper.dialogShow(binding.getRoot().getContext(), dialog.getStyle(), dialog.getTitle(), dialog.getMessage(), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(binding.getRoot().getContext(), LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    });
                }
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
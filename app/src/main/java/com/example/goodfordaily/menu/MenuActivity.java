package com.example.goodfordaily.menu;

import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_OPEN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityMenuBinding;
import com.example.goodfordaily.generated.callback.OnClickListener;
import com.example.goodfordaily.menu.viewModel.MenuViewModel;
import com.example.goodfordaily.util.SaveSharedPreferences;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;
    MenuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MenuViewModel.class);
        binding.setViewModel(viewModel);

        binding.getViewModel().drawer.observe(this, click-> {
            if(click) {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        binding.getViewModel().Todo.observe(this, click-> {
            if(click) {
                Log.e("TAG", "onCreate: " + " HOLY SHIT" );
            }
        });
//        binding.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View drawerView) {
//                binding.getViewModel().userName.setValue(SaveSharedPreferences.getUserName(getApplication()));
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
    }
}
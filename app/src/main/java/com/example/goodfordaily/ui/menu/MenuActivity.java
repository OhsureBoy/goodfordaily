package com.example.goodfordaily.ui.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityMenuBinding;
import com.example.goodfordaily.ui.diary.DiaryFragment;
import com.example.goodfordaily.ui.home.HomeFragment;
import com.example.goodfordaily.ui.login.LoginActivity;
import com.example.goodfordaily.ui.menu.viewModel.MenuViewModel;
import com.example.goodfordaily.ui.todo.TodoFragment;
import com.example.goodfordaily.util.PreferenceManager;
import com.example.goodfordaily.util.onBackPressedListener;

public class MenuActivity extends AppCompatActivity   {

    ActivityMenuBinding binding;
    MenuViewModel viewModel;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MenuViewModel.class);
        binding.setViewModel(viewModel);

        //userName
        viewModel.userName.setValue(getIntent().getStringExtra("userName"));
        PreferenceManager.setString(getApplicationContext(), "loginId", getIntent().getStringExtra("userName"));
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, HomeFragment.class, null)
                .commit();

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, HomeFragment.class, null)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        binding.todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle result = new Bundle();
                result.putString("id", viewModel.userName.getValue());

                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, TodoFragment.class, result)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        binding.diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, DiaryFragment.class, null)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(binding.getRoot().getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.menu_fragment);
        if(fragment != null) {
            ((onBackPressedListener)fragment).onBackPressed();
        }
    }
}
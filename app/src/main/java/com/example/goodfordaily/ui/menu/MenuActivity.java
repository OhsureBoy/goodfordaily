package com.example.goodfordaily.ui.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Gravity;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityMenuBinding;
import com.example.goodfordaily.ui.diary.DiaryFragment;
import com.example.goodfordaily.ui.home.HomeFragment;
import com.example.goodfordaily.ui.menu.viewModel.MenuViewModel;
import com.example.goodfordaily.ui.todo.TodoFragment;

public class MenuActivity extends AppCompatActivity {

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

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, HomeFragment.class, null)
                .commit();

        binding.getViewModel().drawer.observe(this, click-> {
            if(click) {
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        binding.getViewModel().Home.observe(this, click -> {
            if(click) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, HomeFragment.class, null)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        binding.getViewModel().Todo.observe(this, click-> {
            if(click) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, TodoFragment.class, null)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        binding.getViewModel().Diary.observe(this, click-> {
            if(click) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, DiaryFragment.class, null)
                        .commit();

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }
}
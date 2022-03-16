package com.example.goodfordaily.ui.diary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.FragmentDiaryBinding;
import com.example.goodfordaily.ui.diary.adapter.DiaryAdapter;
import com.example.goodfordaily.ui.diary.viewModel.DiaryFragmentViewModel;
import com.example.goodfordaily.ui.home.HomeFragment;
import com.example.goodfordaily.ui.login.LoginActivity;
import com.example.goodfordaily.util.PreferenceManager;
import com.example.goodfordaily.util.onBackPressedListener;

public class DiaryFragment extends Fragment implements onBackPressedListener {

    FragmentDiaryBinding binding;
    DiaryFragmentViewModel viewModel;
    DiaryAdapter diaryAdapter;
    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(DiaryFragmentViewModel.class);
        binding.setViewModel(viewModel);

        diaryAdapter = new DiaryAdapter();
        binding.diaryList.setAdapter(diaryAdapter);

        Log.e("TAG", "onCreateView: " + PreferenceManager.getString(getContext(),"loginId") );
        binding.getViewModel().setTodoRepository(getActivity().getApplication(), PreferenceManager.getString(getContext(),"loginId"));

        binding.getViewModel().getAllTasks().observe(getViewLifecycleOwner(), tasks -> diaryAdapter.setDiaryList(tasks));

        binding.diaryWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.menu_fragment, DiaryWriteFragment.class, null)
                        .commit();
            }
        });
        return binding.getRoot();

    }

    @Override
    public void onBackPressed() {
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.menu_fragment, DiaryFragment.class, null)
                    .commit();
    }
}

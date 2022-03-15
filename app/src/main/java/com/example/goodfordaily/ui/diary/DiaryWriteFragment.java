package com.example.goodfordaily.ui.diary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.FragmentDiaryWriteBinding;
import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.ui.diary.viewModel.DiaryWriteFragmentViewModel;
import com.example.goodfordaily.util.PreferenceManager;
import com.example.goodfordaily.util.onBackPressedListener;

public class DiaryWriteFragment extends Fragment implements onBackPressedListener {

    FragmentDiaryWriteBinding binding;
    DiaryWriteFragmentViewModel viewModel;
    FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_write, container,false);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(DiaryWriteFragmentViewModel.class);
        binding.setViewModel(viewModel);

        binding.getViewModel().setTodoRepository(getActivity().getApplication(), PreferenceManager.getString(getContext(),"loginId"));
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFragment();
            }
        });

        Log.e("TAG", "onCreateView: " + PreferenceManager.getString(getContext(),"loginId") );
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insert(new DiaryModel(binding.writeDiary.getText().toString(), PreferenceManager.getString(getContext(),"loginId")));
                Toast.makeText(getContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                BackFragment();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onBackPressed() {
        BackFragment();
    }

    public void BackFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, DiaryFragment.class, null)
                .commit();
    }

}

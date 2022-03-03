package com.example.goodfordaily.todo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.ActivityMenuBinding;

import com.example.goodfordaily.databinding.FragmentTodoBinding;
import com.example.goodfordaily.todo.viewModel.TodoFragmentViewModel;

public class TodoFragment extends Fragment {
    FragmentTodoBinding binding;
    TodoFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TodoFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();

    }
}

package com.example.goodfordaily.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.goodfordaily.R;

import com.example.goodfordaily.databinding.FragmentTodoBinding;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.ui.todo.adapter.TodoAdapter;
import com.example.goodfordaily.ui.todo.viewModel.TodoFragmentViewModel;

public class TodoFragment extends Fragment {
    FragmentTodoBinding binding;
    TodoFragmentViewModel viewModel;
    TodoAdapter todoAdapter;
    Bundle result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TodoFragmentViewModel.class);
        binding.setViewModel(viewModel);

        todoAdapter = new TodoAdapter();
        result = new Bundle();
        binding.todoView.setAdapter(todoAdapter);


        viewModel. getAllTasks().observe(getViewLifecycleOwner(), tasks -> todoAdapter.setTodoList(tasks));
        binding.addTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getViewModel().insert(new TodoModel(binding.editText.getText().toString().trim(), result.getString("id")));
                binding.editText.setText("");
            }
        });
        return binding.getRoot();

    }
}

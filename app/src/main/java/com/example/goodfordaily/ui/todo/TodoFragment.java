package com.example.goodfordaily.ui.todo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodfordaily.R;

import com.example.goodfordaily.databinding.FragmentTodoBinding;
import com.example.goodfordaily.model.TodoModel;
import com.example.goodfordaily.ui.todo.adapter.TodoAdapter;
import com.example.goodfordaily.ui.todo.viewModel.TodoFragmentViewModel;

public class TodoFragment extends Fragment {
    FragmentTodoBinding binding;
    TodoFragmentViewModel viewModel;
    TodoAdapter todoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(TodoFragmentViewModel.class);
        viewModel.setTodoRepository(getActivity().getApplication() ,getArguments().getString("id") );

        binding.setViewModel(viewModel);


        todoAdapter = new TodoAdapter();
        binding.todoView.setAdapter(todoAdapter);

        viewModel. getAllTasks().observe(getViewLifecycleOwner(), tasks -> todoAdapter.setTodoList(tasks));

        binding.addTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.getViewModel().insert(new TodoModel(binding.editText.getText().toString().trim(), viewModel.getName()));
                Log.e("TAG", "onClick: " + viewModel.getName() );
                binding.editText.setText("");
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //Leave this empty since no Drag-and-Drop functionality implemented
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(todoAdapter.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.todoView);

        return binding.getRoot();

    }
}

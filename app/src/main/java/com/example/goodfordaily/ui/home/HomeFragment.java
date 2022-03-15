package com.example.goodfordaily.ui.home;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.goodfordaily.R;
import com.example.goodfordaily.databinding.FragmentHomeBinding;
import com.example.goodfordaily.model.DiaryModel;
import com.example.goodfordaily.ui.diary.adapter.DiaryAdapter;
import com.example.goodfordaily.ui.home.viewModel.HomeFragmentViewModel;
import com.example.goodfordaily.ui.login.LoginActivity;
import com.example.goodfordaily.ui.todo.adapter.TodoAdapter;
import com.example.goodfordaily.util.PreferenceManager;
import com.example.goodfordaily.util.onBackPressedListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

public class HomeFragment extends Fragment implements onBackPressedListener {

    FragmentHomeBinding binding;
    HomeFragmentViewModel viewModel;

    TodoAdapter todoAdapter;
    DiaryAdapter diaryAdapter;
    String date = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HomeFragmentViewModel.class);
        binding.setViewModel(viewModel);

        todoAdapter = new TodoAdapter();
        diaryAdapter = new DiaryAdapter();

        binding.todoRecycleList.setAdapter(todoAdapter);
        binding.diaryRecycleList.setAdapter(diaryAdapter);

        binding.getViewModel().setDiaryRepository(getActivity().getApplication(), PreferenceManager.getString(getContext(),"loginId"));
        binding.getViewModel().setTodoRepository(getActivity().getApplication(), PreferenceManager.getString(getContext(),"loginId"));
        

        binding.getViewModel().getTodolistAllTasks().observe(getViewLifecycleOwner(), task -> todoAdapter.setTodoList(task));

        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            LocalDate format = LocalDate.of(year, Month.values()[month], dayOfMonth);
            date = format.toString();
            binding.getViewModel().getDiaryAllTasks().observe(getViewLifecycleOwner(), task ->{

                task.forEach(diaryModel ->
                {
                    if (diaryModel.getDate().equals(date))
                        diaryAdapter.setDiaryList(task);
                    else
                        diaryAdapter.setDiaryList(null);
                });
            });
                });
//
//        binding.getViewModel().date.observe(getViewLifecycleOwner(), date -> {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            this.date = format.format(date);
//            Log.e("TAG", "onCreateView: " + date);
//        });

        return binding.getRoot();

    }

    @Override
    public void onBackPressed() {

            Intent intent = new Intent(binding.getRoot().getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
    }

}

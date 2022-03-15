package com.example.goodfordaily.ui.home;

import android.content.Intent;
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
import com.example.goodfordaily.databinding.FragmentHomeBinding;
import com.example.goodfordaily.ui.home.viewModel.HomeFragmentViewModel;
import com.example.goodfordaily.ui.login.LoginActivity;
import com.example.goodfordaily.util.onBackPressedListener;

public class HomeFragment extends Fragment implements onBackPressedListener {

    FragmentHomeBinding binding;
    HomeFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HomeFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();

    }

    @Override
    public void onBackPressed() {

            Intent intent = new Intent(binding.getRoot().getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
    }

}

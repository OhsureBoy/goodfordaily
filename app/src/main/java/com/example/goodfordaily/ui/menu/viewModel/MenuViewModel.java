package com.example.goodfordaily.ui.menu.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MenuViewModel extends AndroidViewModel {
    public MutableLiveData<String> userName;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        userName    = new MutableLiveData<>();
    }

}

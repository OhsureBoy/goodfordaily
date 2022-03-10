package com.example.goodfordaily.ui.menu.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.goodfordaily.repository.login.LoginRepository;
import com.example.goodfordaily.util.SaveSharedPreferences;

import io.reactivex.disposables.CompositeDisposable;

public class MenuViewModel extends AndroidViewModel {
    public MutableLiveData<Boolean> Home;
    public MutableLiveData<Boolean> Todo;
    public MutableLiveData<Boolean> Diary;
    public MutableLiveData<String> userName;
    public MutableLiveData<Boolean> drawer;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        Home        = new MutableLiveData<>();
        Todo        = new MutableLiveData<>();
        Diary       = new MutableLiveData<>();
        userName    = new MutableLiveData<>();
        drawer      = new MutableLiveData<>();
    }


    public void clickedToDoBtn() {
        Log.e("TAG", "onClick: ");
        Todo.setValue(true);
    }

    public void clickedDiaryBtn() {
        Log.e("TAG", "onClick: ");
        Diary.setValue(true);
    }

    public void clickedHomeBtn() {
        Home.setValue(true);
    }

    public void ClickedDrawerBtn() {
        drawer.setValue(true);
    }
}

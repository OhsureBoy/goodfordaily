package com.example.goodfordaily.repository.login;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.goodfordaily.model.LoginModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;


@Dao
public interface LoginDao {

    @Insert
    Completable insert(LoginModel data);

    @Query("select * from LoginDetails")
    LiveData<List<LoginModel>> getDetails();

    @Query("select * from LoginDetails where Email= :mail and password= :password")
    boolean getUserCheck(String mail, String password);

    @Query("select * from LoginDetails where Email= :mail")
    boolean getSameName(String mail);

    @Query("delete from LoginDetails")
    void deleteAllData();

}

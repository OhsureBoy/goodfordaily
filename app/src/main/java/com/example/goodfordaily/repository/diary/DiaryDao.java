package com.example.goodfordaily.repository.diary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.goodfordaily.model.DiaryModel;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface DiaryDao {

    @Insert
    Completable insert(DiaryModel task);

    @Update
    Completable update(DiaryModel task);

    @Delete
    Completable delete(DiaryModel task);

    @Query("DELETE FROM diary_table")
    Completable deleteAllTasks();

    @Query("SELECT * FROM diary_table where name=:name")
    LiveData<List<DiaryModel>> getAllTasks(String name);

    @Query("SELECT * FROM diary_table where name=:name and date=:date")
    LiveData<List<DiaryModel>> getDateTasks(String name, String date);
}